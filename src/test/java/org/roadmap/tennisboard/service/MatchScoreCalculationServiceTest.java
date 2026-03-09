package org.roadmap.tennisboard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.enums.TennisPoint;
import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.model.PlayerScore;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchScoreCalculationServiceTest {
    @Mock
    private OngoingMatchesService ongoingMatchesService;

    @Mock
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @InjectMocks
    private MatchScoreCalculationService matchScoreCalculationService;

    private MatchScore matchScore;

    @BeforeEach
    void setUp() {
        matchScore = new MatchScore(
                new PlayerScore(new Player("boris")),
                new PlayerScore(new Player("vadim"))
        );
    }

    @Test
    void shouldIncreasePointsForPlayerOne() {
        UUID uuidMatch = UUID.randomUUID();

        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(matchScore));

        matchScoreCalculationService.makeMove(uuidMatch, 1);

        assertEquals(TennisPoint.FIFTEEN, matchScore.getPlayerOne().getPoints());
    }

    @Test
    void shouldWinGameForPlayerOne() {
        UUID uuidMatch = UUID.randomUUID();

        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(matchScore));

        matchScore.getPlayerOne().setPoints(TennisPoint.FORTY);

        matchScoreCalculationService.makeMove(uuidMatch, 1);

        assertEquals(1, matchScore.getPlayerOne().getGames());
        assertEquals(TennisPoint.ZERO, matchScore.getPlayerOne().getPoints());
        assertEquals(TennisPoint.ZERO, matchScore.getPlayerTwo().getPoints());
    }

    @Test
    void shouldContinueGameForAdvantagePoints() {
        UUID uuidMatch = UUID.randomUUID();

        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(matchScore));

        matchScore.getPlayerOne().setPoints(TennisPoint.FORTY);
        matchScore.getPlayerTwo().setPoints(TennisPoint.FORTY);

        matchScoreCalculationService.makeMove(uuidMatch, 1);

        assertEquals(TennisPoint.ADVANTAGE, matchScore.getPlayerOne().getPoints());
        assertEquals(TennisPoint.FORTY, matchScore.getPlayerTwo().getPoints());
        assertEquals(0, matchScore.getPlayerOne().getGames());
        assertEquals(0, matchScore.getPlayerTwo().getGames());

    }

    @Test
    void shouldStartTieBreak() {
        UUID uuidMatch = UUID.randomUUID();

        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(matchScore));

        matchScore.getPlayerOne().setGames(5);
        matchScore.getPlayerTwo().setGames(6);
        matchScore.getPlayerOne().setPoints(TennisPoint.FORTY);

        matchScoreCalculationService.makeMove(uuidMatch, 1);

        assertTrue(matchScore.isTieBreak());
        assertEquals(6, matchScore.getPlayerOne().getGames());
        assertEquals(6, matchScore.getPlayerTwo().getGames());
    }

}