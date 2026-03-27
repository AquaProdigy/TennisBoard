package org.roadmap.tennisboard.service;


import org.roadmap.tennisboard.model.tennis.TennisMatch;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OngoingMatchesService {
    private final ConcurrentHashMap<UUID, TennisMatch> matches =  new ConcurrentHashMap<>();

    public Optional<TennisMatch> getMatch(UUID matchId) {
        return Optional.ofNullable(matches.get(matchId));
    }

    public void addMatch(UUID matchId, TennisMatch ongoingMatch) {
        matches.put(matchId, ongoingMatch);
    }

    public void removeMatch(UUID matchId) {
        matches.remove(matchId);
    }

}
