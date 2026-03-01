package org.roadmap.tennisboard.exception;

public class MatchNotFound extends RuntimeException {
    public MatchNotFound() {
        super("Match not found");
    }
}
