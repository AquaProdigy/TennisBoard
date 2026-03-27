package org.roadmap.tennisboard.model.tennis;

import lombok.Getter;
import org.roadmap.tennisboard.model.AbstractTennisLevel;
import org.roadmap.tennisboard.model.enums.TennisPoint;


public class TennisGame extends AbstractTennisLevel {
    @Getter
    private TennisPoint firstPlayerPoints = TennisPoint.ZERO;
    @Getter
    private TennisPoint secondPlayerPoints = TennisPoint.ZERO;

    public TennisGame(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    public void awardPointTo(TennisPlayer player) {
        TennisPoint winnerPlayerPoints = this.getCurrentTennisPoints(player);

        boolean handled = this.hasWonGame(player);

        if (!handled) {
            this.setPoint(player, winnerPlayerPoints.next());
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

    private boolean hasWonGame(TennisPlayer winnerPlayer) {
        TennisPlayer loserPlayer = winnerPlayer == firstPlayer ? secondPlayer : firstPlayer;

        TennisPoint loserPlayerPoints = this.getCurrentTennisPoints(loserPlayer);
        TennisPoint winnerPlayerPoints = this.getCurrentTennisPoints(winnerPlayer);

        if (winnerPlayerPoints == TennisPoint.ADVANTAGE) {
            this.winner = winnerPlayer;
            this.resetPlayerPoints();
            return true;
        }

        if (loserPlayerPoints == TennisPoint.ADVANTAGE) {
            this.setPoint(loserPlayer, TennisPoint.FORTY);
            return true;
        }

        if (winnerPlayerPoints == TennisPoint.FORTY && loserPlayerPoints != TennisPoint.FORTY) {
            this.winner = winnerPlayer;
            this.resetPlayerPoints();
            return true;
        }

        return false;

    }

    private void resetPlayerPoints() {
        this.firstPlayerPoints = TennisPoint.ZERO;
        this.secondPlayerPoints = TennisPoint.ZERO;
    }

    private void setPoint(TennisPlayer player, TennisPoint points) {
        if (player == this.firstPlayer) {
            this.firstPlayerPoints = points;
        } else {
            this.secondPlayerPoints = points;
        }
    }

    private TennisPoint getCurrentTennisPoints(TennisPlayer player) {
        return player == this.firstPlayer ? this.firstPlayerPoints : this.secondPlayerPoints;
    }
}
