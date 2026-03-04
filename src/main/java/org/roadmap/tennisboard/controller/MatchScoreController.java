package org.roadmap.tennisboard.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.enums.MoveResult;
import org.roadmap.tennisboard.exception.MatchNotFoundException;
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
            @RequestParam("uuid") UUID uuid,
            Model model
    ) {
        MatchScore matchScore = ongoingMatchesService.getMatch(uuid)
                .orElseThrow(() -> new MatchNotFoundException("Match not found"));

        model.addAttribute("matchScore", matchScore);
        model.addAttribute("matchUuid", uuid.toString());

        return "match-score";
    }


    @PostMapping
    public String tennisStroke(
            @RequestParam("uuid") UUID uuid,
            @NotNull @RequestParam("player") int player,
            RedirectAttributes redirectAttributes
    ) {
        MoveResult moveResult = matchScoreCalculationService.makeMove(uuid, player);

        if (moveResult.equals(MoveResult.FINISHED)) {
            return "redirect:/matches";
        }

        redirectAttributes.addAttribute("uuid", uuid);
        return "redirect:/match-score";
    }
}
