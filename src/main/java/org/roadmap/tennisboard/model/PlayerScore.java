package org.roadmap.tennisboard.model;

import lombok.Getter;
import lombok.Setter;
import org.roadmap.tennisboard.entity.Player;
import org.roadmap.tennisboard.enums.TennisPoint;

import java.util.Objects;


@Getter
@Setter
public class PlayerScore {
    private Player player;
    private int games;
    private int sets;
    private TennisPoint points;
    private int tieBreakPoints;

    private PlayerScore(Player player) {
        this.player = player;
        this.games = 0;
        this.sets = 0;
        this.points = TennisPoint.ZERO;
        this.tieBreakPoints = 0;
    }

    public static PlayerScore create(Player player) {
        return new PlayerScore(player);
    }

    public void resetPoints() {
        this.points = TennisPoint.ZERO;
        this.tieBreakPoints = 0;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerScore that = (PlayerScore) o;
        return games == that.games && sets == that.sets && points == that.points && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, games, sets, points);
    }

}
