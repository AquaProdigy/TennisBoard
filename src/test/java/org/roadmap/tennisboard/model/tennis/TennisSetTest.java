package org.roadmap.tennisboard.model.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TennisSetTest {
    private TennisPlayer firstPlayer;
    private TennisPlayer secondPlayer;
    private TennisSet tennisSet;


    @BeforeEach
    void setUp() {
        this.firstPlayer = new TennisPlayer("boris");
        this.secondPlayer = new TennisPlayer("vadim");

        this.tennisSet = new TennisSet(this.firstPlayer, this.secondPlayer);
    }

    @Test
    void shouldIncrementOneGamesForFirstPlayer() {
        winGames(firstPlayer, 1);

        assertEquals(1, this.tennisSet.getFirstPlayerGames());

    }

    @Test
    void shouldWinGameForFirstPlayer() {
        winGames(firstPlayer, 6);

        assertEquals(6, this.tennisSet.getFirstPlayerGames());
        assertTrue(this.tennisSet.isFinished());
    }

    @Test
    void shouldStartTieBreakWithSixSixPoints() {
        winGames(firstPlayer, 5);
        winGames(secondPlayer, 5);

        winGames(firstPlayer, 1);
        winGames(secondPlayer, 1);

        assertTrue(tennisSet.isTieBreak());

    }

    @Test
    void shouldWinFirstPlayerWithExtraPoints() {
        winGames(firstPlayer, 5);
        winGames(secondPlayer, 5);

        winGames(firstPlayer, 2);

        assertTrue(tennisSet.isFinished());
        assertEquals(this.firstPlayer, tennisSet.getWinner());
        assertEquals(7, tennisSet.getFirstPlayerGames());
        assertEquals(5, tennisSet.getSecondPlayerGames());

    }


    private void winGame(TennisPlayer player) {
        tennisSet.awardPointTo(player);
        tennisSet.awardPointTo(player);
        tennisSet.awardPointTo(player);
        tennisSet.awardPointTo(player);
    }

    private void winGames(TennisPlayer player, int count) {
        for (int i = 0; i < count; i++) {
            winGame(player);
        }
    }
}