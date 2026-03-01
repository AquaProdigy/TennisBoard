package org.roadmap.tennisboard.service;


import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.enums.TennisPoint;
import org.roadmap.tennisboard.exception.MatchNotFound;
import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.model.PlayerScore;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.roadmap.tennisboard.enums.TennisPoint.FORTY;
import static org.roadmap.tennisboard.enums.TennisPoint.ZERO;

@Service
@RequiredArgsConstructor
public class MatchScoreCalculationService {
    private static final String PLAYER_ONE = "player1";

    private final OngoingMatchesService ongoingMatchesService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    public void makeMove(String uuid, String player) {
        MatchScore match = ongoingMatchesService
                .getMatchByMatchId(UUID.fromString(uuid))
                .orElseThrow(MatchNotFound::new);

        PlayerScore winner;
        PlayerScore loser;

        if (player.equals(PLAYER_ONE)) {
            winner = match.getPlayerOne();
            loser = match.getPlayerTwo();
        } else {
            winner = match.getPlayerTwo();
            loser = match.getPlayerOne();
        }

        scorePoint(match, winner, loser);
    }

    private void scorePoint(MatchScore match, PlayerScore winner, PlayerScore loser) {

        if (isTieBreak(match)) {
            scoreTieBreak(match, winner, loser);
            return;
        }

        if (winner.getPoints() == TennisPoint.ADVANTAGE) {
            winGame(match, winner, loser);
            return;
        }

        if (loser.getPoints() == TennisPoint.ADVANTAGE) {
            loser.setPoints(TennisPoint.FORTY);
            return;
        }

        if (winner.getPoints() == TennisPoint.FORTY &&
                loser.getPoints() == TennisPoint.FORTY) {
            winner.setPoints(TennisPoint.ADVANTAGE);
            return;
        }

        if (winner.getPoints() == FORTY) {
            winner.setPoints(TennisPoint.ADVANTAGE);
        }

        winner.setPoints(winner.getPoints().next());
    }

    private void scoreTieBreak(MatchScore match, PlayerScore winner, PlayerScore loser) {
        winner.setTieBreakPoints(winner.getTieBreakPoints() + 1);

        if (winner.getTieBreakPoints() >= 7 &&
                winner.getTieBreakPoints() - loser.getTieBreakPoints() >= 2) {

            winGame(match, winner, loser);
            match.setTieBreak(false);
        }
    }

    private boolean isTieBreak(MatchScore match) {
        return match.isTieBreak();
    }


    private void winGame(MatchScore match, PlayerScore winner, PlayerScore loser) {
        winner.setGames(winner.getGames() + 1);

        resetPoints(match);

        if (isTieBreakStart(match)) {
            match.setTieBreak(true);
            return;
        }

        if (hasWonSet(winner, loser)) {
            winSet(match, winner, loser);
        }
    }

    private void winSet(MatchScore match, PlayerScore winner, PlayerScore loser) {
        winner.setSets(winner.getSets() + 1);

        resetGames(match);

        if (winner.getSets() == 2) {
            finishedMatchesPersistenceService.finishMatch(match, winner, loser);
        }
    }

    private void resetGames(MatchScore match) {
        match.getPlayerOne().setGames(0);
        match.getPlayerTwo().setGames(0);
    }

    private boolean isTieBreakStart(MatchScore match) {
        return match.getPlayerOne().getGames() == 6 &&  match.getPlayerTwo().getGames() == 6;
    }

    private boolean hasWonSet(PlayerScore winner, PlayerScore loser) {
        return (winner.getGames() >= 6 && winner.getGames() - loser.getGames() >= 2) ||
                winner.getGames() == 7;
    }

    private void resetPoints(MatchScore match) {
        match.getPlayerOne().resetPoints();
        match.getPlayerTwo().resetPoints();
    }


}
