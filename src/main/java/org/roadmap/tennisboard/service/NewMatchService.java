package org.roadmap.tennisboard.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.dto.CreateMatchRequest;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.model.OngoingMatch;
import org.roadmap.tennisboard.model.PlayerScore;
import org.roadmap.tennisboard.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewMatchService {
    private final PlayerRepository playerRepository;
    private final OngoingMatchesService ongoingMatchesService;

    @Transactional
    public UUID createMatch(CreateMatchRequest request) {
        Player firstPlayer = playerRepository.findByNameIgnoreCase(request.getFirstPlayer())
                .orElseGet(() -> playerRepository.save(new Player(request.getFirstPlayer())));

        Player secondPlayer = playerRepository.findByNameIgnoreCase(request.getSecondPlayer())
                .orElseGet(() -> playerRepository.save(new Player(request.getSecondPlayer())));

        OngoingMatch ongoingMatch = new OngoingMatch(
                new PlayerScore(firstPlayer.getName()),
                new PlayerScore(secondPlayer.getName())
        );

        UUID matchId = UUID.randomUUID();

        ongoingMatchesService.addMatch(matchId, ongoingMatch);

        return matchId;

    }
}
