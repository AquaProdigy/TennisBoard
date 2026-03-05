package org.roadmap.tennisboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.roadmap.tennisboard.dto.CreateMatchRequest;
import org.roadmap.tennisboard.service.NewMatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;


@Controller
@RequestMapping("/new-match")
@RequiredArgsConstructor
public class NewMatchController {
    private final NewMatchService newMatchService;

    @GetMapping
    public String newMatchPage(Model model) {
        model.addAttribute("request", new CreateMatchRequest());
        return "new-match";
    }

    @PostMapping
    public String createNewMatch(
            @Valid @ModelAttribute("request") CreateMatchRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "new-match";
        }

        UUID uuid = newMatchService.createMatch(request);
        redirectAttributes.addAttribute("uuid", uuid);

        return "redirect:/match-score";
    }

}
