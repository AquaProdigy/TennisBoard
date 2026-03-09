package org.roadmap.tennisboard.advice;

import org.roadmap.tennisboard.exception.MatchNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex, Model model) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "Unknown error";
        model.addAttribute("errorMessage", msg);
        return "error";
    }
}
