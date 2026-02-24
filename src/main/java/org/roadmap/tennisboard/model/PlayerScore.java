package org.roadmap.tennisboard.model;

import org.roadmap.tennisboard.entity.Player;

import java.util.Objects;

public class PlayerScore {
    private Player player;
    private int games;
    private int sets;
    private int points;

    private PlayerScore(Player player, int games, int sets, int points) {
        this.player = player;
        this.games = games;
        this.sets = sets;
        this.points = points;
    }

    public static PlayerScore createNewPlayerScore(Player player) {
        return new PlayerScore(player, 0, 0, 0);
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

    public Player getPlayer() {
        return player;
    }

    public int getGames() {
        return games;
    }

    public int getSets() {
        return sets;
    }

    public int getPoints() {
        return points;
    }
}
