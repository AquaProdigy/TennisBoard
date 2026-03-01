package org.roadmap.tennisboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.dto.match.CreateMatchRequest;
import org.roadmap.tennisboard.model.MatchScore;
import org.roadmap.tennisboard.service.NewMatchService;
import org.roadmap.tennisboard.service.OngoingMatchesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/new-match")
@RequiredArgsConstructor
public class MatchController {
    private final NewMatchService matchService;
    private final OngoingMatchesService ongoingMatchService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("request", new CreateMatchRequest());
        return "new-match";
    }

    @PostMapping
    public String createNewMatch(
            @Valid @ModelAttribute("request") CreateMatchRequest request,
            RedirectAttributes redirectAttributes
    ) {
        MatchScore matchScore = matchService.createMatch(request);
        Optional<UUID> uuid = ongoingMatchService.getMatchByMatchScore(matchScore);

        if (uuid.isPresent()) {
            redirectAttributes.addAttribute("uuid", uuid.get());
            return "redirect:/match-score";
        }

        return "redirect:/match-score";

    }

}
