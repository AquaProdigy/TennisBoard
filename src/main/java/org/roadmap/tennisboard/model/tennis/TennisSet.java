package org.roadmap.tennisboard.model.tennis;

import lombok.Getter;
import org.roadmap.tennisboard.model.AbstractTennisLevel;
import org.roadmap.tennisboard.model.Game;


public class TennisSet extends AbstractTennisLevel {
    private static final int GAMES_TO_WIN = 6;
    private static final int GAMES_TO_WIN_TIEBREAK = 7;
    private static final int MIN_DIFF_TO_WIN = 2;

    @Getter private int firstPlayerGames;
    @Getter private int secondPlayerGames;
    @Getter private TennisPlayer winner;

    private Game currentGame;

    public TennisSet(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    public void awardPointTo(TennisPlayer player) {
        if (isFinished()) {
            throw new IllegalStateException("Set is already finished");
        }
        if (currentGame == null) {
            currentGame = new TennisGame(firstPlayer, secondPlayer);
        }

        currentGame.awardPointTo(player);

        if (currentGame.isFinished()) {
            TennisPlayer gameWinner = currentGame.getWinner(); // сохраняем до resolveNextGame
            incrementGames(gameWinner);
            currentGame = resolveNextGame(gameWinner);
        }
    }

    public boolean isFinished() {
        return winner != null;
    }

    public boolean isTieBreak() {
        return currentGame instanceof TennisTieBreak;
    }

    public String getFirstPlayerPoints() {
        if (currentGame instanceof TennisGame game) {
            return game.getFirstPlayerPoints().getValue();
        }

        if (currentGame instanceof TennisTieBreak tieBreak) {
            return String.valueOf(tieBreak.getFirstPlayerPoints());
        }

        return "0";
    }

    public String getSecondPlayerPoints() {
        if (currentGame instanceof TennisGame game) {
            return game.getSecondPlayerPoints().getValue();
        }

        if (currentGame instanceof TennisTieBreak tieBreak) {
            return String.valueOf(tieBreak.getSecondPlayerPoints());
        }

        return "0";
    }

    private Game resolveNextGame(TennisPlayer gameWinner) {
        if (hasWonSet(gameWinner)) {
            return null;
        }

        if (firstPlayerGames == GAMES_TO_WIN && secondPlayerGames == GAMES_TO_WIN) {
            return new TennisTieBreak(firstPlayer, secondPlayer);
        }

        return new TennisGame(firstPlayer, secondPlayer);
    }

    private boolean hasWonSet(TennisPlayer candidate) {
        int wins = getGames(candidate);
        int losses = getGames(getOpponent(candidate));

        boolean won = (wins >= GAMES_TO_WIN && wins - losses >= MIN_DIFF_TO_WIN)
                || wins == GAMES_TO_WIN_TIEBREAK;

        if (won) {
            winner = candidate;
        }

        return won;
    }

    private void incrementGames(TennisPlayer player) {
        if (player == firstPlayer) {
            firstPlayerGames++;
        } else {
            secondPlayerGames++;
        }
    }

    private int getGames(TennisPlayer player) {
        return player == firstPlayer ? firstPlayerGames : secondPlayerGames;
    }
}
