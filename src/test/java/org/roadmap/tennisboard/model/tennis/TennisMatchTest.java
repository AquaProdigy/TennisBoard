package org.roadmap.tennisboard.model.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TennisMatchTest {
    private TennisPlayer firstPlayer;
    private TennisPlayer secondPlayer;
    private TennisMatch tennisMatch;

    @BeforeEach
    void setUp() {
        this.firstPlayer = new TennisPlayer("boris");
        this.secondPlayer = new TennisPlayer("vadim");

        this.tennisMatch = new TennisMatch(firstPlayer, secondPlayer);
    }


    @Test
    void shouldIncrementOneSetToFirstPlayer() {
        winGames(this.firstPlayer, 6);

        assertEquals(1, tennisMatch.getFirstPlayerSets());
        assertFalse(tennisMatch.isFinished());
    }

    @Test
    void shouldWinMatchToFirstPlayer() {
        winGames(this.firstPlayer, 6);
        winGames(this.firstPlayer, 6);

        assertEquals(2, tennisMatch.getFirstPlayerSets());
        assertEquals(0, tennisMatch.getSecondPlayerSets());
        assertEquals(this.firstPlayer, tennisMatch.getWinner());
        assertTrue(tennisMatch.isFinished());
    }



    private void winGame(TennisPlayer player) {
        tennisMatch.awardPointTo(player);
        tennisMatch.awardPointTo(player);
        tennisMatch.awardPointTo(player);
        tennisMatch.awardPointTo(player);
    }

    private void winGames(TennisPlayer player, int count) {
        for (int i = 0; i < count; i++) {
            winGame(player);
        }
    }

}