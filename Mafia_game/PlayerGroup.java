import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerGroup {
    private List<Player> group = new ArrayList<>();
    private List<Integer> memberNum = new ArrayList<Integer>();

    PlayerGroup (List<Player> players) {
        for (Player p : players) {
            group.add(p);
            memberNum.add(p.NUM);
        }
    }

    int vote() {
        int playerCount = Game.totalPlayers;
        Random r = new Random();
        int num = r.nextInt(playerCount);
        if ((group.size() > 0) && (group.get(0) instanceof Mafia || group.get(0) instanceof Detective)) {
            // class comparison
            if (memberNum.contains(num)) {
                return vote();
            }
        }
        return num;
    }

    List<Player> getGroup() {
        return group;
    }

    public void setGroup(List<Player> group) {
        this.group = group;
    }

    public void removeMember(int arg) {

        memberNum.remove(Integer.valueOf(arg));
        
        Player toRemove = null;
        for (Player p : group) {
            if (p.NUM == arg) {
                toRemove = p;
            }
        }
        if (toRemove != null) {
            group.remove(toRemove);
            // if (getActualClass() != null) {
            //     System.out.println(getActualClass().getName() + " " + group.size() +  " " + arg + " " + memberNum.size());
            // } else {
            //     System.out.println("hello");
            // }
        }

    }

    Class<?> getActualClass() {
        // generic programming
        if (group.size() == 0) {
            return null;
        }
        return group.get(0).getClass();
    }
}
