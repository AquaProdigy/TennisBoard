package org.roadmap.tennisboard.dto;


import lombok.Getter;
import lombok.Setter;
import org.roadmap.tennisboard.entity.Player;

@Getter
@Setter
public class MatchDto {
    private String namePlayer1;
    private String namePlayer2;
    private String nameWinner;
}
