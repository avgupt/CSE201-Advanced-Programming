import java.util.HashMap;
import java.util.Scanner;

public class Round {
    private HashMap<Integer, Player> players;
    private static int roundCounter = 0;
    private final int ROUND_NUM;
    private Player user;

    Scanner in = new Scanner(System.in);

    Round(HashMap<Integer, Player> players, Player user) {
        Round.roundCounter++;
        ROUND_NUM = roundCounter;
        this.players = players;
        this.user = user;
    }

    public void gameplay() {
        PlayerGroup mafias = Game.mafiaGroup;
        PlayerGroup detectives = Game.detectiveGroup;
        PlayerGroup healers = Game.healerGroup;

        int targetNum;
        if (user != null && user.getClass() == Mafia.class) {
            System.out.println("Choose a target:");
            targetNum = selectTraget();
        } else {
            targetNum = chooseTarget(mafias);
            System.out.println("Mafias have chosen a target.");
        }
        // System.out.println("Mafias " + targetNum);
        Player mafiaTarget = players.get(targetNum);
        mafiaTarget.setHP(Mafia.kill(mafiaTarget.getHP()));
        players.replace(targetNum, mafiaTarget);
        
        boolean isMafiaRemoved = false;
        Player voteOutTarget = null;
        if (detectives.getGroup().size() > 0) {
            if (user != null && user.getClass() == Detective.class) {
                System.out.println("Choose a player to test:");
                targetNum = selectTraget();
            } else {
                targetNum = chooseTarget(detectives);
                System.out.println("Detectives have chosen a player to test.");
            }
            voteOutTarget = players.get(targetNum);
            if (players.get(targetNum).getClass() == Mafia.class) {
                isMafiaRemoved = true;
                players.remove(targetNum);
            }
            // System.out.println("Detective " + targetNum);
        }

        if (healers.getGroup().size() > 0) {
            if (user != null && user.getClass() == Healer.class) {
                System.out.println("Choose a player to heal:");
                targetNum = voteByUser();
            } else {
                targetNum = chooseTarget(healers);
                System.out.println("Healers have chosen someone to heal.");
            }
            Player healerTarget = players.get(targetNum);
            healerTarget.setHP(Healer.heal(healerTarget.getHP()));
            players.replace(targetNum, healerTarget);
            // System.out.println("Healers " + targetNum);
        }

        System.out.println("--End of actions--");

        if (players.get(mafiaTarget.NUM).HP <= 0) {
            System.out.println(mafiaTarget + " has died.");
            removeMember(mafiaTarget.NUM);
            players.remove(mafiaTarget.NUM);
        } else {
            System.out.println("No one died.");
        }

        if (!isMafiaRemoved) {
            int highestVote = vote();
            voteOutTarget = players.get(highestVote);
            players.remove(highestVote);
        }
        removeMember(voteOutTarget.NUM);
        System.out.println(voteOutTarget + " has been voted out.");

        System.out.println("--End of " + this + "--\n");
    }

    private void removeMember(int num) {
        Game.detectiveGroup.removeMember(num);
        Game.healerGroup.removeMember(num);
    }

    private int selectTraget() {
        int targetNum = voteByUser();
        while (players.get(targetNum).getClass() == user.getClass()) {
            System.out.println("You cannot choose another " + user.getClass().getName() + ". Choose again.");
            targetNum = voteByUser();
        }
        return targetNum;  
    }

    private int voteByUser() {
        
        int vote = in.nextInt();
        
        if (!players.containsKey(vote)) {
            System.out.println("Invalid player. Choose again.");
            return voteByUser();
        }
        return vote;
    }

    private int vote() {
        int votes[] = new int[Game.totalPlayers+1];

        if (user != null) {
            System.out.println("Select a person to vote out:");
            int userVote = in.nextInt();
            while (! players.containsKey(userVote)) {
                System.out.println("Vote for a valid player. Try again.");
                userVote = in.nextInt();
            }
            votes[userVote]++;            
            
        }

        for (Player p : players.values()) {
            if (user != null && p.NUM == user.NUM) {
                continue;
            }

            int vote = p.vote();
            while (! players.containsKey(vote)) {
                vote = p.vote();
            }
            votes[vote]++;
        }

        int max = -1;
        int index = -1;
        for (int i = 1; i <= Game.totalPlayers; i++) {
            if (votes[i] > max) {
                max = votes[i];
                index = i;
            }
        }

        for (int i = 1; i <= Game.totalPlayers; i++) {
            if (i != index && votes[i] == max) {
                if (user != null) {
                    System.out.println("There was a tie. Vote again.");
                }
                return vote();
            }
        }

        return index;
    }

    private int chooseTarget(PlayerGroup p) {
        int target = p.vote();
        while (!players.containsKey(target)) {
            target = p.vote();
        }
        return target;
    }

    public HashMap<Integer, Player> getPlayers() {
        return players;
    }

    public Player getUser() {
        if (user != null && players.containsKey(user.NUM)) {
            return user;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Round " + this.ROUND_NUM; 
    }
}
