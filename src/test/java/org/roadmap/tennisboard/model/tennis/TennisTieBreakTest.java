package org.roadmap.tennisboard.model.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TennisTieBreakTest {
    private TennisPlayer firstPlayer;
    private TennisPlayer secondPlayer;
    private TennisTieBreak tennisTieBreak;

    @BeforeEach
    void setUp() {
        this.firstPlayer = new TennisPlayer("boris");
        this.secondPlayer = new TennisPlayer("vadim");

        this.tennisTieBreak = new TennisTieBreak(firstPlayer, secondPlayer);
    }

    @Test
    void shouldIncrementTiebreakPointToFirstPlayer() {
        this.tennisTieBreak.awardPointTo(firstPlayer);

        assertEquals(1, this.tennisTieBreak.getFirstPlayerPoints());
    }

    @Test
    void shouldWinTiebreakForFirstPlayer() {
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);

        assertEquals(this.firstPlayer, this.tennisTieBreak.getWinner());
        assertTrue(this.tennisTieBreak.isFinished());
    }

    @Test
    void shouldWinWithExtraPointsTiebreak() {
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);


        this.tennisTieBreak.awardPointTo(secondPlayer);
        this.tennisTieBreak.awardPointTo(secondPlayer);
        this.tennisTieBreak.awardPointTo(secondPlayer);
        this.tennisTieBreak.awardPointTo(secondPlayer);
        this.tennisTieBreak.awardPointTo(secondPlayer);
        this.tennisTieBreak.awardPointTo(secondPlayer);

        this.tennisTieBreak.awardPointTo(firstPlayer);
        this.tennisTieBreak.awardPointTo(firstPlayer);

        assertEquals(this.firstPlayer, this.tennisTieBreak.getWinner());
        assertTrue(this.tennisTieBreak.isFinished());
    }
}