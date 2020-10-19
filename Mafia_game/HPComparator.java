import java.util.Comparator;

public class HPComparator implements Comparator<Player>{
    @Override
    public int compare(Player arg0, Player arg1) {
        return arg0.HP - arg1.HP;
    }
}
