package org.roadmap.tennisboard.mapper;

import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.entity.Match;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.model.OngoingMatch;
import org.roadmap.tennisboard.model.PlayerScore;
import org.roadmap.tennisboard.repository.PlayerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OngoingMatchMapper {
    private final PlayerRepository playerRepository;

    public Match toEntity(OngoingMatch match) {
        Player firstPlayer = findPlayer(match.getFirstPlayer().getPlayerName());
        Player secondPlayer = findPlayer(match.getSecondPlayer().getPlayerName());
        Player winner = findPlayer(match.getWinner().getPlayerName());

        return new Match(firstPlayer, secondPlayer, winner);
    }

    private Player findPlayer(String name) {
        return playerRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalStateException("Player not found: " + name));
    }
}
