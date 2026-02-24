package org.roadmap.tennisboard.model;


import java.util.Objects;
import java.util.UUID;

public class MatchScore {
    private final PlayerScore playerOne;
    private final PlayerScore playerTwo;

    private MatchScore(PlayerScore playerOne, PlayerScore playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public static MatchScore createNewMatch(PlayerScore playerOne, PlayerScore playerTwo) {
        return new MatchScore(
                playerOne,
                playerTwo
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatchScore that = (MatchScore) o;
        return Objects.equals(playerOne, that.playerOne) && Objects.equals(playerTwo, that.playerTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerOne, playerTwo);
    }

    public PlayerScore getPlayerOne() {
        return playerOne;
    }

    public PlayerScore getPlayerTwo() {
        return playerTwo;
    }
}

