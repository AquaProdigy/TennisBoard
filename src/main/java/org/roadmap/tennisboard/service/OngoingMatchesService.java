package org.roadmap.tennisboard.service;


import org.roadmap.tennisboard.model.MatchScore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OngoingMatchesService {
    private final Map<UUID, MatchScore> matches =  new HashMap<>();

    public Optional<UUID> getMatchByMatchScore(MatchScore matchScore) {
        return matches.entrySet().stream()
                .filter(entry -> entry.getValue().equals(matchScore))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public Optional<MatchScore> getMatchByMatchId(UUID matchId) {
        return Optional.ofNullable(matches.get(matchId));
    }


    public void addMatch(UUID matchId, MatchScore matchScore) {
        matches.put(matchId, matchScore);
    }

    public void removeMatchScore(MatchScore matchScore) {
        matches.entrySet()
                .removeIf(entry -> entry.getValue().equals(matchScore));
    }

}
