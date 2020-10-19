public class Healer extends Player{
    private static PlayerGroup healers;

    Healer(int num) {
        super(num);
        HP = 800;
    }

    public static PlayerGroup setHealers(PlayerGroup healers) {
        if ((healers.getGroup().size() == 0) || !(healers.getActualClass() == Healer.class)) {
            // class comparison
            return null;
        }
        Healer.healers = healers;
        return Healer.healers;
    }

    public static int heal(int targetHP) {
        return targetHP + 500;
    }

}
