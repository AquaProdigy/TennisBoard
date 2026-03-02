package org.roadmap.tennisboard.service;


import org.roadmap.tennisboard.model.MatchScore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OngoingMatchesService {
    private final ConcurrentHashMap<UUID, MatchScore> matches =  new ConcurrentHashMap<>();

    public Optional<MatchScore> getMatch(UUID matchId) {
        return Optional.ofNullable(matches.get(matchId));
    }

    public void addMatch(UUID matchId, MatchScore matchScore) {
        matches.put(matchId, matchScore);
    }

    public void removeMatch(UUID matchId) {
        matches.remove(matchId);
    }

}
