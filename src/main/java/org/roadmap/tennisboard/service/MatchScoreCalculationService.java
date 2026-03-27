package org.roadmap.tennisboard.service;


import org.roadmap.tennisboard.model.tennis.TennisMatch;
import org.roadmap.tennisboard.model.tennis.TennisPlayer;
import org.springframework.stereotype.Service;

@Service
public class MatchScoreCalculationService {
    private static final int PLAYER_ONE = 1;

    public void makeMove(TennisMatch match, int playerNumber) {
        TennisPlayer winner = playerNumber == PLAYER_ONE
                ? match.getFirstPlayer()
                : match.getSecondPlayer();

        match.awardPointTo(winner);
    }
}
