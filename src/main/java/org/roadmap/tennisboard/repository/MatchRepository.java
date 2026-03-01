package org.roadmap.tennisboard.repository;

import org.roadmap.tennisboard.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
