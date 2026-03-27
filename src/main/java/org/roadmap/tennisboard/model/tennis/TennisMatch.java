package org.roadmap.tennisboard.model.tennis;

import lombok.Getter;
import org.roadmap.tennisboard.model.AbstractTennisLevel;
import org.roadmap.tennisboard.model.Game;

public class TennisMatch extends AbstractTennisLevel {
    private static final int SETS_TO_WIN = 2;

    @Getter private int firstPlayerSets;
    @Getter private int secondPlayerSets;

    private Game currentSet;

    public TennisMatch(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    public void awardPointTo(TennisPlayer player) {
        if (isFinished()) {
            throw new IllegalStateException("Match is already finished");
        }
        if (currentSet == null) {
            currentSet = new TennisSet(firstPlayer, secondPlayer);
        }

        currentSet.awardPointTo(player);

        if (currentSet.isFinished()) {
            TennisPlayer setWinner = currentSet.getWinner();
            incrementSets(setWinner);
            currentSet = resolveNextSet(setWinner);
        }
    }

    public boolean isFinished() {
        return winner != null;
    }

    @Override
    public TennisPlayer getWinner() {
        return this.winner;
    }

    public int getFirstPlayerGames() {
        return currentSet != null ? ((TennisSet) currentSet).getFirstPlayerGames() : 0;
    }

    public int getSecondPlayerGames() {
        return currentSet != null ? ((TennisSet) currentSet).getSecondPlayerGames() : 0;
    }

    public String getFirstPlayerPoints() {
        return currentSet != null ? ((TennisSet) currentSet).getFirstPlayerPoints() : "0";
    }

    public String getSecondPlayerPoints() {
        return currentSet != null ? ((TennisSet) currentSet).getSecondPlayerPoints() : "0";
    }

    private Game resolveNextSet(TennisPlayer setWinner) {
        if (hasWonMatch(setWinner)) {
            return null;
        }
        return new TennisSet(firstPlayer, secondPlayer);
    }

    private boolean hasWonMatch(TennisPlayer player) {
        int sets = getSets(player);

        if (sets >= SETS_TO_WIN) {
            this.winner = player;
            return true;
        }
        return false;
    }

    private void incrementSets(TennisPlayer player) {
        if (player == firstPlayer) {
            firstPlayerSets++;
        } else {
            secondPlayerSets++;
        }
    }

    private int getSets(TennisPlayer player) {
        return player == firstPlayer ? firstPlayerSets : secondPlayerSets;
    }
}
