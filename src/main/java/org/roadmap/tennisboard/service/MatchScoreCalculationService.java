package org.roadmap.tennisboard.service;


import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.model.OngoingMatch;
import org.roadmap.tennisboard.model.TennisPoint;
import org.roadmap.tennisboard.exception.MatchNotFoundException;
import org.roadmap.tennisboard.model.PlayerScore;
import org.springframework.stereotype.Service;
import java.util.UUID;

import static org.roadmap.tennisboard.model.TennisPoint.FORTY;


@Service
public class MatchScoreCalculationService {
    private static final int PLAYER_ONE = 1;

    public void makeMove(OngoingMatch match, int playerNumber) {
        PlayerScore winner = playerNumber == PLAYER_ONE
                ? match.getFirstPlayer()
                : match.getSecondPlayer();

        match.awardPointTo(winner);
    }
}
