//package org.roadmap.tennisboard.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.roadmap.tennisboard.entity.Player;
//import org.roadmap.tennisboard.model.OngoingMatch;
//import org.roadmap.tennisboard.model.TennisPoint;
//import org.roadmap.tennisboard.model.PlayerScore;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class MatchScoreCalculationServiceTest {
//    @Mock
//    private OngoingMatchesService ongoingMatchesService;
//
//    @Mock
//    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
//
//    @InjectMocks
//    private MatchScoreCalculationService matchScoreCalculationService;
//
//    private OngoingMatch ongoingMatch;
//
//    @BeforeEach
//    void setUp() {
//        ongoingMatch = new OngoingMatch(
//                new PlayerScore(new Player("boris")),
//                new PlayerScore(new Player("vadim"))
//        );
//    }
//
//    @Test
//    void shouldIncreasePointsForPlayerOne() {
//        UUID uuidMatch = UUID.randomUUID();
//
//        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(ongoingMatch));
//
//        matchScoreCalculationService.makeMove(uuidMatch, 1);
//
//        assertEquals(TennisPoint.FIFTEEN, ongoingMatch.getPlayerOne().getPoints());
//    }
//
//    @Test
//    void shouldWinGameForPlayerOne() {
//        UUID uuidMatch = UUID.randomUUID();
//
//        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(ongoingMatch));
//
//        ongoingMatch.getPlayerOne().setPoints(TennisPoint.FORTY);
//
//        matchScoreCalculationService.makeMove(uuidMatch, 1);
//
//        assertEquals(1, ongoingMatch.getPlayerOne().getGames());
//        assertEquals(TennisPoint.ZERO, ongoingMatch.getPlayerOne().getPoints());
//        assertEquals(TennisPoint.ZERO, ongoingMatch.getPlayerTwo().getPoints());
//    }
//
//    @Test
//    void shouldContinueGameForAdvantagePoints() {
//        UUID uuidMatch = UUID.randomUUID();
//
//        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(ongoingMatch));
//
//        ongoingMatch.getPlayerOne().setPoints(TennisPoint.FORTY);
//        ongoingMatch.getPlayerTwo().setPoints(TennisPoint.FORTY);
//
//        matchScoreCalculationService.makeMove(uuidMatch, 1);
//
//        assertEquals(TennisPoint.ADVANTAGE, ongoingMatch.getPlayerOne().getPoints());
//        assertEquals(TennisPoint.FORTY, ongoingMatch.getPlayerTwo().getPoints());
//        assertEquals(0, ongoingMatch.getPlayerOne().getGames());
//        assertEquals(0, ongoingMatch.getPlayerTwo().getGames());
//
//    }
//
//    @Test
//    void shouldStartTieBreak() {
//        UUID uuidMatch = UUID.randomUUID();
//
//        when(ongoingMatchesService.getMatch(uuidMatch)).thenReturn(Optional.of(ongoingMatch));
//
//        ongoingMatch.getPlayerOne().setGames(5);
//        ongoingMatch.getPlayerTwo().setGames(6);
//        ongoingMatch.getPlayerOne().setPoints(TennisPoint.FORTY);
//
//        matchScoreCalculationService.makeMove(uuidMatch, 1);
//
//        assertTrue(ongoingMatch.isTieBreak());
//        assertEquals(6, ongoingMatch.getPlayerOne().getGames());
//        assertEquals(6, ongoingMatch.getPlayerTwo().getGames());
//    }
//
//}