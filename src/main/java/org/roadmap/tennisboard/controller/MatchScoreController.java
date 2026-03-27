package org.roadmap.tennisboard.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.exception.MatchNotFoundException;
import org.roadmap.tennisboard.model.tennis.TennisMatch;
import org.roadmap.tennisboard.service.FinishedMatchesPersistenceService;
import org.roadmap.tennisboard.service.MatchScoreCalculationService;
import org.roadmap.tennisboard.service.OngoingMatchesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;


@Controller
@RequestMapping("/match-score")
@RequiredArgsConstructor
public class MatchScoreController {
    private static final String VIEW_NAME = "match-score";
    private static final String MATCH_SCORE_ATTR = "matchScore";
    private static final String UUID_ATTR = "uuid";
    private static final String REDIRECT_MATCHES = "redirect:/matches";
    private static final String REDIRECT_MATCH_SCORE = "redirect:/match-score";

    private final OngoingMatchesService ongoingMatchesService;
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @GetMapping
    public String matchScore(
            @RequestParam(UUID_ATTR) UUID uuid,
            Model model
    ) {
        TennisMatch match = getMatchOrThrow(uuid);
        model.addAttribute(MATCH_SCORE_ATTR, match);
        return VIEW_NAME;
    }

    @PostMapping
    public String tennisStroke(
            @RequestParam(UUID_ATTR) UUID uuid,
            @Min(1) @Max(2) @RequestParam("player") int player,
            RedirectAttributes redirectAttributes
    ) {
        TennisMatch match = getMatchOrThrow(uuid);

        matchScoreCalculationService.makeMove(match, player);

        if (match.isFinished()) {
            finishedMatchesPersistenceService.finishMatch(match, uuid);
            return REDIRECT_MATCHES;
        }

        redirectAttributes.addAttribute(UUID_ATTR, uuid);
        return REDIRECT_MATCH_SCORE;
    }

    private TennisMatch getMatchOrThrow(UUID uuid) {
        return ongoingMatchesService.getMatch(uuid)
                .orElseThrow(() -> new MatchNotFoundException("Match not found"));
    }
}
