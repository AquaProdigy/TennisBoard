package org.roadmap.tennisboard.advice;

import org.roadmap.tennisboard.exception.MatchNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MatchNotFoundException.class)
    public String matchNotFound(Model model) {
        model.addAttribute("errorHand", "Match not found");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex, Model model) {
        model.addAttribute("errorHand", "Exception: " + ex.getMessage().substring(0, 50));
        return "error";
    }
}
