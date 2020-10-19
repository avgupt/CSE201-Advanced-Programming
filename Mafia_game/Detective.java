public class Detective extends Player{
    private static PlayerGroup detectives;

    Detective(int num) {
        super(num);
        HP = 800;
    }

    public static PlayerGroup setDetectives(PlayerGroup detectives) {
        if ((detectives.getGroup().size() == 0) || !(detectives.getActualClass() == Detective.class)) {
            // class comparison
            return null;
        }
        Detective.detectives = detectives;
        return Detective.detectives;
    }

}
