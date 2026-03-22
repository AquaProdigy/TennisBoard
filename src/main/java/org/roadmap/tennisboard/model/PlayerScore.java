package org.roadmap.tennisboard.model;

import lombok.Getter;

@Getter
public class PlayerScore {
    private static final int INITIAL_TIEBREAK_POINTS = 0;
    private static final int INITIAL_GAMES = 0;
    private static final int INITIAL_SETS = 0;


    private final String playerName;
    private int games;
    private int sets;
    private TennisPoint points;
    private int tieBreakPoints;

    public PlayerScore(String playerName) {
        this.playerName = playerName;
        this.games = INITIAL_GAMES;
        this.sets = INITIAL_SETS;
        this.points = TennisPoint.ZERO;
        this.tieBreakPoints = INITIAL_TIEBREAK_POINTS;
    }

    protected void incrementGames() {
        this.games++;
    }

    protected void incrementSets() {
        this.sets++;
    }

    protected void incrementTieBreakPoints() {
        this.tieBreakPoints++;
    }

    protected void resetGames() {
        this.games = INITIAL_GAMES;
    }

    protected void setPoints(TennisPoint point) {
        this.points = point;
    }


    protected void resetPoints() {
        this.points = TennisPoint.ZERO;
    }

    protected void resetTieBreakPoints() {
        this.tieBreakPoints = INITIAL_TIEBREAK_POINTS;
    }





}
