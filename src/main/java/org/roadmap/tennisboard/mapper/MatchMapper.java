package org.roadmap.tennisboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.roadmap.tennisboard.dto.MatchDto;
import org.roadmap.tennisboard.entity.Match;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Mapping(source = "player1.name", target = "namePlayer1")
    @Mapping(source = "player2.name", target = "namePlayer2")
    @Mapping(source = "winner.name", target = "nameWinner")
    MatchDto toDto(Match match);
}
