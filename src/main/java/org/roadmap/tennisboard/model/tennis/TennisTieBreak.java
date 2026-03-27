package org.roadmap.tennisboard.model.tennis;

import lombok.Getter;
import org.roadmap.tennisboard.model.AbstractTennisLevel;


public class TennisTieBreak extends AbstractTennisLevel {
    private static final int TIEBREAK_WIN_POINTS = 7;
    private static final int MIN_DIFF_FOR_WIN = 2;
    private static final int INIT_TIEBREAK_POINTS = 0;

    @Getter private int firstPlayerPoints = INIT_TIEBREAK_POINTS;
    @Getter private int secondPlayerPoints = INIT_TIEBREAK_POINTS;

    public TennisTieBreak(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    public void awardPointTo(TennisPlayer player) {
        this.incrementPoints(player);

        boolean isWinTieBreak = this.hasWonTieBreak(player);

        if (isWinTieBreak) {
            this.winner = player;
        }

    }

    @Override
    public boolean isFinished() {
        return this.winner != null;
    }

    @Override
    public TennisPlayer getWinner() {
        return this.winner;
    }

    private boolean hasWonTieBreak(TennisPlayer winner) {
        TennisPlayer loser = winner == firstPlayer ? secondPlayer : firstPlayer;

        int winnerPoints = getPoints(winner);
        int loserPoints = getPoints(loser);

        return winnerPoints >= TIEBREAK_WIN_POINTS && winnerPoints - loserPoints >= MIN_DIFF_FOR_WIN;
    }

    private int getPoints(TennisPlayer player) {
        return player == firstPlayer ? firstPlayerPoints : secondPlayerPoints;
    }

    private void incrementPoints(TennisPlayer player) {
        if (player == firstPlayer) {
            firstPlayerPoints++;
        } else {
            secondPlayerPoints++;
        }
    }
}
