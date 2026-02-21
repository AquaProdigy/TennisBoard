package org.roadmap.tennisboard.controller;

import jakarta.validation.Valid;
import org.roadmap.tennisboard.dto.match.CreateMatchRequest;
import org.roadmap.tennisboard.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/new-match")
    public String index(Model model) {
        model.addAttribute("request", new CreateMatchRequest());
        return "new-match";
    }

    @PostMapping("/new-match")
    public void createNewMatch(@Valid @ModelAttribute("request") CreateMatchRequest request) {
        matchService.createMatch(request);
    }

}
