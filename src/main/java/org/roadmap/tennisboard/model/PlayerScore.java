package org.roadmap.tennisboard.model;

import lombok.Data;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.enums.TennisPoint;

@Data
public class PlayerScore {
    private Player player;
    private int games;
    private int sets;
    private TennisPoint points;
    private int tieBreakPoints;

    public PlayerScore(Player player) {
        this.player = player;
        this.games = 0;
        this.sets = 0;
        this.points = TennisPoint.ZERO;
        this.tieBreakPoints = 0;
    }

    public void resetPoints() {
        this.points = TennisPoint.ZERO;
        this.tieBreakPoints = 0;
    }
}
