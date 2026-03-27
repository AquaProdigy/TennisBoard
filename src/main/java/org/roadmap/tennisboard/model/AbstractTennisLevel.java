package org.roadmap.tennisboard.model;

import lombok.Getter;
import org.roadmap.tennisboard.model.tennis.TennisPlayer;

public abstract class AbstractTennisLevel implements Game {
    @Getter protected final TennisPlayer firstPlayer;
    @Getter protected final TennisPlayer secondPlayer;

    protected TennisPlayer winner;

    protected AbstractTennisLevel(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    protected TennisPlayer getOpponent(TennisPlayer player) {
        return player == firstPlayer ? secondPlayer : firstPlayer;
    }

}
