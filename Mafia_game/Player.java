import java.util.Random;

public abstract class Player {
    final int NUM;
    Random r = new Random();
    protected int HP;

    Player (int num) {
        this.NUM = num;
    }

    int vote() {
        int num = Game.totalPlayers;
        return r.nextInt(num);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int hP) {
        HP = hP;
    }
        
    @Override
    public String toString() {
        return "Player" + this.NUM; 
    }
}
