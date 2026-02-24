package org.roadmap.tennisboard.service;

import org.roadmap.tennisboard.dto.match.CreateMatchRequest;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.model.PlayerScore;
import org.roadmap.tennisboard.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchService {
    private final PlayerRepository playerRepository;
    private final Map<UUID, MatchScore> matches = new HashMap<>();

    public MatchService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public Optional<MatchScore> getMatchScore(UUID uuid) {
        return  Optional.ofNullable(matches.get(uuid));
    }

    public Optional<UUID> getMatchUuidByMatchScore(MatchScore matchScore) {
        return matches.entrySet().stream()
                .filter(entry -> entry.getValue().equals(matchScore))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public MatchScore createMatch(CreateMatchRequest request) {
        Optional<Player> playerOne = playerRepository.findByName(request.getPlayer1());
        Optional<Player> playerTwo = playerRepository.findByName(request.getPlayer2());

        if (playerOne.isEmpty()) {
            playerOne = Optional.of((Player) playerRepository.save(new Player(request.getPlayer1())));
        }

        if (playerTwo.isEmpty()) {
            playerTwo = Optional.of((Player) playerRepository.save(new Player(request.getPlayer2())));
        }


        MatchScore matchScore = MatchScore.createNewMatch(
                PlayerScore.createNewPlayerScore(playerOne.get()),
                PlayerScore.createNewPlayerScore(playerTwo.get())
        );

        matches.put(UUID.randomUUID(), matchScore);

        return matchScore;

    }
}
