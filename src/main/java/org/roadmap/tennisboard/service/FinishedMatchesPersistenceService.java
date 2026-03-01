package org.roadmap.tennisboard.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.entity.Match;
import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.model.PlayerScore;
import org.roadmap.tennisboard.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {
    private final OngoingMatchesService ongoingMatchesService;
    private final MatchRepository matchRepository;


    @Transactional
    public void finishMatch(MatchScore match, PlayerScore winner, PlayerScore loser) {
        ongoingMatchesService.removeMatchScore(match);

        matchRepository.save(new Match(
                match.getPlayerOne().getPlayer(),
                match.getPlayerTwo().getPlayer(),
                winner.getPlayer()
        ));
    }

}
