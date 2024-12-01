package br.edu.utfpr.td.tsi.health_center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootRedirectController {

    @GetMapping("")
    public String redirectToConsultation() {
        return "redirect:/consultation";
    }
}