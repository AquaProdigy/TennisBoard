package org.roadmap.tennisboard.repository;

import org.roadmap.tennisboard.entity.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m JOIN FETCH m.player1 JOIN FETCH m.player2 LEFT JOIN FETCH m.winner WHERE LOWER(m.player1.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(m.player2.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Match> findAllByPlayerNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("SELECT m FROM Match m JOIN FETCH m.player1 JOIN FETCH m.player2 LEFT JOIN FETCH m.winner")
    Page<Match> findAllMatches(Pageable pageable);
}
