import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {
    static int totalPlayers;

    int mafiaCount;
    private int playerCount;
    private Player user;
    
    static Scanner in = new Scanner(System.in);
    static Random r = new Random();
    static PlayerGroup mafiaGroup;
    static PlayerGroup detectiveGroup;
    static PlayerGroup healerGroup;

    private HashMap<Integer, Player> players;

    Game(int playerCount, Player user) {
        totalPlayers = playerCount;
        this.playerCount = playerCount;
        this.user = user;

        mafiaCount = playerCount/5;

        players = new HashMap<Integer, Player>(playerCount);
        for (int i = 1; i <= playerCount; i++) {
            players.put(i, null);
        }

        createPlayers();
    }

    private Player getPlayerObject(int i, int num) {
        Player p;
        switch (i) {
            case 0:
                p = new Commoner(num);
                return p;
            case 1:
                p = new Detective(num);
                return p;
            case 2:
                p = new Healer(num);
                return p;
            default:
                p = new Mafia(num);
                return p;
        }
    }

    private HashMap<String, Integer> increasePlayerCount(String className, HashMap<String, Integer> map) {
        map.replace(className, map.get(className) + 1);
        return map;
    }

    private void createPlayers() {
        HashMap<String, Integer> playerClassCount = new HashMap<String, Integer>(4);
        playerClassCount.put("Commoner", 0);
        playerClassCount.put("Detective", 0);
        playerClassCount.put("Healer", 0);
        playerClassCount.put("Mafia", 0);


        if (user == null) {
            user = getPlayerObject(r.nextInt(4), r.nextInt(playerCount) + 1);
        }
        players.replace(user.NUM, user);
        playerClassCount = increasePlayerCount(user.getClass().getName(), playerClassCount);

        while (playerClassCount.get("Mafia") < playerCount / 5) {
            int num = r.nextInt(playerCount) + 1;
            if (players.get(num) == null) {
                players.replace(num, new Mafia(num));
                playerClassCount = increasePlayerCount("Mafia", playerClassCount);
            }
        }

        while (playerClassCount.get("Detective") < playerCount / 5) {
            int num = r.nextInt(playerCount) + 1;
            if (players.get(num) == null) {
                players.replace(num, new Detective(num));
                playerClassCount = increasePlayerCount("Detective", playerClassCount);
            }
        }

        while (playerClassCount.get("Healer") < Math.max(1, playerCount / 10)) {
            int num = r.nextInt(playerCount) + 1;
            if (players.get(num) == null) {
                players.replace(num, new Healer(num));
                playerClassCount = increasePlayerCount("Healer", playerClassCount);
            }
        }

        for (int num = 1; num <= playerCount; num++) {
            if (players.get(num) == null) {
                players.replace(num, new Commoner(num));
                playerClassCount = increasePlayerCount("Commoner", playerClassCount);
            }
        }

        createGroups();
    }

    private void createGroups() {
        List<Player> mafiaList = new ArrayList<Player>();
        List<Player> detectiveList = new ArrayList<Player>();
        List<Player> healerList = new ArrayList<Player>();

        for (Player p: players.values()) {
            if (p instanceof Mafia) {
                mafiaList.add(p);
            } else if (p instanceof Detective) {
                detectiveList.add(p);
            } else if (p instanceof Healer) {
                healerList.add(p);
            }
        }

        PlayerGroup mafias = new PlayerGroup(mafiaList);
        PlayerGroup detectives = new PlayerGroup(detectiveList);
        PlayerGroup healers = new PlayerGroup(healerList);

        mafiaGroup= Mafia.setMafias(mafias);
        detectiveGroup = Detective.setDetectives(detectives);
        healerGroup = Healer.setHealers(healers);
    }

    boolean doesMafiaWin() {
        int nonMafiaCount = players.size() - mafiaGroup.getGroup().size();
        return (float) mafiaCount / nonMafiaCount == 1.0;
    }

    static Player chooseCharacter(int playerCount) {

        System.out.println(
            "Choose a Character\n" + 
            "1) Mafia\n" + "2) Detective\n" + 
            "3) Healer\n" + "4) Commoner\n" +
            "5) Asssign Randomly");

        int n = in.nextInt();
        int num = r.nextInt(playerCount) + 1;

        switch (n) {
            case 1:
                return new Mafia(num);
            case 2:
                return new Detective(num);
            case 3:
                return new Healer(num);
            case 4:
                return new Commoner(num);
            case 5:
                return null;
            default:
                System.out.println("Incorrect option. Choose a number between 1-5.");
                return chooseCharacter(playerCount);
        }
    }

    static void printGroupMembers (PlayerGroup group, Player user) {
        for (Player p : group.getGroup()) {
            if (p.NUM == user.NUM) {
                continue;
            }
            System.out.print(p + ", ");
        }
        System.out.print("\b\b  ]\n");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Mafia");

        int playerCount = 0;
        while (playerCount < 6) {
            System.out.println("Enter Number of Players:");
            playerCount = in.nextInt();

            if (playerCount < 6) {
                System.out.println("Player count must be atleast 6.");
            }
        }

        Player user = chooseCharacter(playerCount);
        Game game = new Game(playerCount, user);
        
        user = game.user;
        System.out.println("You are " + user);
        System.out.print(
            "You are a " + user.getClass().getName() + 
            ". " + "Other " + user.getClass().getName() + 
            "s are: [  "
            );
        if (user.getClass() == Mafia.class) {
            printGroupMembers(Game.mafiaGroup, user);
        } else if (user.getClass() == Detective.class) {
            printGroupMembers(Game.detectiveGroup, user);
        } else if (user.getClass() == Healer.class) {
            printGroupMembers(Game.healerGroup, user);
        } else {
            System.out.print("\b  ]\n");
        }

        Round round;
        while (!game.doesMafiaWin() && Mafia.getCount() > 0) {
            if (game.mafiaCount == 0) break;
            // System.out.println(user);

            round = new Round(game.players, game.user);
            System.out.println(round);

            System.out.print(game.players.size() + " players are remaining: ");
            for (Player p : game.players.values()) {
                System.out.print(p + ", ");
            }
            System.out.print("\b\b are alive. \n");

            round.gameplay();
            
            game.players = round.getPlayers();
            game.user = round.getUser();

            // System.out.println("\nMAFIA GROUP SIZE " + mafiaGroup.getGroup().size());
            // System.out.println(Arrays.asList(game.players));
        }

        System.out.println("\nGame Over.");

        if (game.doesMafiaWin()) {
            System.out.println("Mafias won");
        } else {
            System.out.println("Mafias lost");
        }

        in.close();
    }

}