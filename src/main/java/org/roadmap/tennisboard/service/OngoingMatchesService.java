package org.roadmap.tennisboard.service;


import org.roadmap.tennisboard.model.OngoingMatch;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OngoingMatchesService {
    private final ConcurrentHashMap<UUID, OngoingMatch> matches =  new ConcurrentHashMap<>();

    public Optional<OngoingMatch> getMatch(UUID matchId) {
        return Optional.ofNullable(matches.get(matchId));
    }

    public void addMatch(UUID matchId, OngoingMatch ongoingMatch) {
        matches.put(matchId, ongoingMatch);
    }

    public void removeMatch(UUID matchId) {
        matches.remove(matchId);
    }

}
