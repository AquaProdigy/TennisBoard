package org.roadmap.tennisboard.model;

import lombok.Data;

@Data
public class MatchScore {
    private final PlayerScore playerOne;
    private final PlayerScore playerTwo;
    private boolean tieBreak;
}

