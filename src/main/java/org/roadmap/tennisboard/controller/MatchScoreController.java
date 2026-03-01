package org.roadmap.tennisboard.controller;

import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.service.MatchScoreCalculationService;
import org.roadmap.tennisboard.service.OngoingMatchesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/match-score")
@RequiredArgsConstructor
public class MatchScoreController {
    private final OngoingMatchesService ongoingMatchesService;
    private final MatchScoreCalculationService matchScoreCalculationService;


    @GetMapping
    public String matchScore(
            @RequestParam("uuid") String uuid,
            Model model
    ) {
        Optional<MatchScore> matchScore = ongoingMatchesService.getMatchByMatchId(UUID.fromString(uuid));

        matchScore.ifPresent(score -> model.addAttribute("matchScore", score));
        model.addAttribute("matchUuid", UUID.fromString(uuid));

        return "match-score";
    }


    @PostMapping
    public String tennisStroke(
            @RequestParam("uuid") String uuid,
            @RequestParam("player") String player,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("uuid", UUID.fromString(uuid));
        matchScoreCalculationService.makeMove(uuid, player);
        return "redirect:/match-score";
    }
}
