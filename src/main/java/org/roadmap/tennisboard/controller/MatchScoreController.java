package org.roadmap.tennisboard.controller;

import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/match-score")
public class MatchScoreController {
    private final MatchService matchService;

    @Autowired
    public MatchScoreController(MatchService matchService) {
        this.matchService = matchService;
    }


    @GetMapping
    public String matchScore(
            @RequestParam("uuid") String uuid,
            Model model
    ) {
        Optional<MatchScore> matchScore = matchService.getMatchScore(UUID.fromString(uuid));
        matchScore.ifPresent(score -> model.addAttribute("matchScore", score));

        return "match-score";
    }
}
