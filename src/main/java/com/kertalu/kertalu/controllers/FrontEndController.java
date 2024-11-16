package com.kertalu.kertalu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FrontEndController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    @GetMapping("/subscription-tier-manager")
    public String subscriptionTierManager() {
        return "redirect:/subscription_tier_manager.html";
    }

}

