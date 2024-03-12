package educative.lld.tennis;
public class TennisMatch {
    Player player1;
    Player player2;
    GameState currentState;

    public TennisMatch() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentState = new RegularScore(this); // Initial state
    }

    public void pointWonBy(String playerName) {
        Player player = playerName.equals("player1") ? player1 : player2;
        currentState.pointWonBy(player);
    }

    public void setWinner(Player player) {
        System.out.println(player.getName() + " wins the game");
        // Reset points and potentially update state
    }

    // Method to change state
    public void changeState(GameState newState) {
        currentState = newState;
    }

    // Additional methods...

    public static void main(String[] args) {
        TennisMatch match = new TennisMatch();
//        match.incrementScore("player1");
//        match.displayScore();
//        match.incrementScore("player2");
//        match.displayScore();
        // Add more increments as needed to simulate a match
    }
}

