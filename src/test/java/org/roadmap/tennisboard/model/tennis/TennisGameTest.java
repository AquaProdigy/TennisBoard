package org.roadmap.tennisboard.model.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.roadmap.tennisboard.model.enums.TennisPoint;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {
    private TennisPlayer firstPlayer;
    private TennisPlayer secondPlayer;
    private TennisGame tennisGame;

    @BeforeEach
    void setUp() {
        this.firstPlayer = new TennisPlayer("boris");
        this.secondPlayer = new TennisPlayer("vadim");
        this.tennisGame = new TennisGame(firstPlayer, secondPlayer);
    }

    @Test
    void shouldIncrementPointsFromZeroToFifteen() {
        this.tennisGame.awardPointTo(firstPlayer);
        assertEquals(TennisPoint.FIFTEEN, tennisGame.getFirstPlayerPoints());
    }

    @Test
    void shouldIncrementPointsFromZeroToThirty() {
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);

        assertEquals(TennisPoint.THIRTY, tennisGame.getFirstPlayerPoints());
    }

    @Test
    void shouldIncrementPointsFromZeroToForty() {
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);

        assertEquals(TennisPoint.FORTY, tennisGame.getFirstPlayerPoints());
    }

    @Test
    void shouldIncrementPointsFromZeroToAdvantageAndWinGame() {
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);

        assertTrue(tennisGame.isFinished());
        assertEquals(firstPlayer, tennisGame.getWinner());
    }

    @Test
    void shouldIncrementPointFromAdvantageToForty() {
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(firstPlayer);

        this.tennisGame.awardPointTo(secondPlayer);
        this.tennisGame.awardPointTo(secondPlayer);
        this.tennisGame.awardPointTo(secondPlayer);

        this.tennisGame.awardPointTo(firstPlayer);
        this.tennisGame.awardPointTo(secondPlayer);

        assertEquals(TennisPoint.FORTY, tennisGame.getFirstPlayerPoints());
        assertEquals(TennisPoint.FORTY, tennisGame.getSecondPlayerPoints());
    }



}