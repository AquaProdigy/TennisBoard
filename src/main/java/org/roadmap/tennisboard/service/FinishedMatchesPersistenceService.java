package org.roadmap.tennisboard.service;


import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.mapper.OngoingMatchMapper;
import org.roadmap.tennisboard.model.OngoingMatch;
import org.roadmap.tennisboard.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {
    private final OngoingMatchesService ongoingMatchesService;
    private final MatchRepository matchRepository;
    private final OngoingMatchMapper mapper;

    @Transactional
    public void finishMatch(OngoingMatch match, UUID matchId) {
        matchRepository.save(mapper.toEntity(match));
        ongoingMatchesService.removeMatch(matchId);
    }

}
