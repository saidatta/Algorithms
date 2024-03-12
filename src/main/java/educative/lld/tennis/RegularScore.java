package educative.lld.tennis;

public class RegularScore implements GameState {
    private final TennisMatch match;

    public RegularScore(TennisMatch match) {
        this.match = match;
    }

    @Override
    public void pointWonBy(Player player) {
        player.incrementPoints();
        checkGameWinner();
    }

    @Override
    public void checkGameWinner() {
        // Implementation of regular scoring logic
        if (match.player1.getPoints() >= 4 || match.player2.getPoints() >= 4) {
            if (Math.abs(match.player1.getPoints() - match.player2.getPoints()) >= 2) {
                match.setWinner(match.player1.getPoints() > match.player2.getPoints() ? match.player1 : match.player2);
            }
        }
    }

}
// Additional states like Deuce, Advantage, etc., will have similar structures with specific logic

