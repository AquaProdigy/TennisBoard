package org.roadmap.tennisboard.service;

import org.roadmap.tennisboard.dto.match.CreateMatchRequest;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService {
    private final PlayerRepository playerRepository;



    public MatchService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void createMatch(CreateMatchRequest request) {


    }
}
