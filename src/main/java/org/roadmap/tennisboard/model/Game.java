package org.roadmap.tennisboard.model;

import org.roadmap.tennisboard.model.tennis.TennisPlayer;

public interface Game {
    void awardPointTo(TennisPlayer player);
    boolean isFinished();
    TennisPlayer getWinner();
}