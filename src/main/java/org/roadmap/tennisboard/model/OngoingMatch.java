package org.roadmap.tennisboard.model;


import lombok.Getter;

@Getter
public class OngoingMatch {
    private static final int GAMES_FOR_TIEBREAK = 6;
    private static final int POINTS_FOR_TIEBREAK_WIN = 7;
    private static final int MIN_LEAD_TO_WIN = 2;

    private final PlayerScore firstPlayer;
    private final PlayerScore secondPlayer;
    private boolean tieBreak;

    public OngoingMatch(PlayerScore firstPlayer, PlayerScore secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public void awardPointTo(PlayerScore winner) {
        PlayerScore loser = getOpponent(winner);

        if (tieBreak) {
            scoreTieBreak(winner, loser);
        } else {
            scorePoint(winner, loser);
        }
    }

    public boolean isFinished() {
        return firstPlayer.getSets() == 2 || secondPlayer.getSets() == 2;
    }

    public PlayerScore getWinner() {
        if (!isFinished()) {
            throw new IllegalStateException("Match is not finished yet");
        }
        return firstPlayer.getSets() == 2 ? firstPlayer : secondPlayer;
    }

    private void scorePoint(PlayerScore winner, PlayerScore loser) {
        if (winner.getPoints() == TennisPoint.ADVANTAGE) {
            winGame(winner, loser);
            return;
        }
        if (loser.getPoints() == TennisPoint.ADVANTAGE) {
            loser.setPoints(TennisPoint.FORTY);
            return;
        }
        if (winner.getPoints() == TennisPoint.FORTY && loser.getPoints() == TennisPoint.FORTY) {
            winner.setPoints(TennisPoint.ADVANTAGE);
            return;
        }
        if (winner.getPoints() == TennisPoint.FORTY) {
            winGame(winner, loser);
            return;
        }
        winner.setPoints(winner.getPoints().next());
    }

    private void scoreTieBreak(PlayerScore winner, PlayerScore loser) {
        winner.incrementTieBreakPoints();

        if (hasWonTieBreak(winner, loser)) {
            tieBreak = false;
            firstPlayer.resetTieBreakPoints();
            secondPlayer.resetTieBreakPoints();
            winGame(winner, loser);
        }
    }

    private void winGame(PlayerScore winner, PlayerScore loser) {
        winner.incrementGames();
        firstPlayer.resetPoints();
        secondPlayer.resetPoints();

        if (isTieBreakStart()) {
            tieBreak = true;
            return;
        }

        if (hasWonSet(winner, loser)) {
            winner.incrementSets();
            firstPlayer.resetGames();
            secondPlayer.resetGames();
        }
    }

    private boolean hasWonTieBreak(PlayerScore winner, PlayerScore loser) {
        return winner.getTieBreakPoints() >= POINTS_FOR_TIEBREAK_WIN &&
                winner.getTieBreakPoints() - loser.getTieBreakPoints() >= MIN_LEAD_TO_WIN;
    }

    private boolean isTieBreakStart() {
        return firstPlayer.getGames() == GAMES_FOR_TIEBREAK &&
                secondPlayer.getGames() == GAMES_FOR_TIEBREAK;
    }

    private boolean hasWonSet(PlayerScore winner, PlayerScore loser) {
        return (winner.getGames() >= GAMES_FOR_TIEBREAK &&
                winner.getGames() - loser.getGames() >= MIN_LEAD_TO_WIN) ||
                winner.getGames() == 7;
    }

    private PlayerScore getOpponent(PlayerScore player) {
        return player == firstPlayer ? secondPlayer : firstPlayer;
    }

}
