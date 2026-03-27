package org.roadmap.tennisboard.mapper;

import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.entity.Match;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.model.tennis.TennisMatch;
import org.roadmap.tennisboard.repository.PlayerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OngoingMatchMapper {
    private final PlayerRepository playerRepository;

    public Match toEntity(TennisMatch match) {
        Player firstPlayer = findPlayer(match.getFirstPlayer().name());
        Player secondPlayer = findPlayer(match.getSecondPlayer().name());
        Player winner = findPlayer(match.getWinner().name());

        return new Match(firstPlayer, secondPlayer, winner);
    }

    private Player findPlayer(String name) {
        return playerRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalStateException("Player not found: " + name));
    }
}
