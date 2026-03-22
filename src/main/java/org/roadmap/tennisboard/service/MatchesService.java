package org.roadmap.tennisboard.service;

import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.dto.MatchDto;
import org.roadmap.tennisboard.mapper.MatchMapper;
import org.roadmap.tennisboard.repository.MatchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MatchesService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public Page<MatchDto> getMatches(String filterName, Pageable pageable) {
        if (filterName == null || filterName.strip().isBlank()) {
            return matchRepository.findAllMatches(pageable)
                    .map(matchMapper::toDto);
        }

        return matchRepository.findAllByPlayerName(filterName, pageable)
                .map(matchMapper::toDto);
    }
}
