import java.util.Collections;
import java.util.List;

public class Mafia extends Player {
    private static PlayerGroup mafias;
    private static int count = 0;

    Mafia(int num) {
        super(num);
        HP = 2500;
    }

    public static PlayerGroup setMafias(PlayerGroup mafias) {
        if ((mafias.getGroup().size() == 0) || !(mafias.getActualClass() == Mafia.class)) {
            // class comparison
            return null;
        }
        Mafia.mafias = mafias;
        count = mafias.getGroup().size();
        return Mafia.mafias;
    }

    public static int getCount() {
        return count;
    }

    public static int kill(int targetHP) {
        int totalHP = 0;
        for (Player m : mafias.getGroup()) {
            totalHP += m.HP;
        }
        reduceHP(targetHP);
        if (totalHP >= targetHP) {
            return 0;
        } else {
            return targetHP - totalHP;
        }
    }

    private static void reduceHP(int num) {
        int denom = mafias.getGroup().size();
        
        List<Player> group = mafias.getGroup();
        Collections.sort(group, new HPComparator());    // custom comparator

        for (Player p : group) {
            // System.out.print(p.HP + " ");
            if (num / denom > p.HP) {
                num -= p.HP;
                if (p.HP != 0) count--;
                p.setHP(0);
            } else {
                p.setHP(p.getHP() - num / denom);
                num = num - (num / denom);
            }
            denom--;
        }
        System.out.println();
        mafias.setGroup(group);
    }

}
