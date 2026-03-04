package org.roadmap.tennisboard.controller;


import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.dto.MatchDto;
import org.roadmap.tennisboard.service.MatchesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchesController {
    private final MatchesService matchesService;

    @GetMapping
    public String matches(
            @RequestParam(required = false, defaultValue = "") String filterName,
            Pageable pageable,
            Model model
    ) {
        Page<MatchDto> matches = matchesService.getMatches(filterName, pageable);
        model.addAttribute("matches", matches);
        model.addAttribute("filterName", filterName);
        return "matches";
    }
}
