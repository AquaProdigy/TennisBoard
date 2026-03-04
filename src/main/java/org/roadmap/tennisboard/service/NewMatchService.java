package org.roadmap.tennisboard.service;

import jakarta.transaction.Transactional;
import org.roadmap.tennisboard.dto.CreateMatchRequest;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.model.PlayerScore;
import org.roadmap.tennisboard.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NewMatchService {
    private final PlayerRepository playerRepository;
    private final OngoingMatchesService ongoingMatchesService;

    public NewMatchService(PlayerRepository playerRepository, OngoingMatchesService ongoingMatchesService) {
        this.playerRepository = playerRepository;
        this.ongoingMatchesService = ongoingMatchesService;
    }


    @Transactional
    public UUID createMatch(CreateMatchRequest request) {
        Player playerOne = playerRepository.findByName(request.getPlayer1())
                .orElseGet(() -> playerRepository.save(new Player(request.getPlayer1())));

        Player playerTwo = playerRepository.findByName(request.getPlayer2())
                .orElseGet(() -> playerRepository.save(new Player(request.getPlayer2())));

        MatchScore matchScore = MatchScore.createNewMatch(
                PlayerScore.create(playerOne),
                PlayerScore.create(playerTwo)
        );

        UUID randomUuidMatch = UUID.randomUUID();

        ongoingMatchesService.addMatch(randomUuidMatch, matchScore);

        return randomUuidMatch;

    }
}
