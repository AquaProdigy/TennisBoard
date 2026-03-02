package org.roadmap.tennisboard.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class MatchScore {
    private final PlayerScore playerOne;
    private final PlayerScore playerTwo;

    private boolean tieBreak;

    public MatchScore(PlayerScore playerOne, PlayerScore playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.tieBreak = false;
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

}

