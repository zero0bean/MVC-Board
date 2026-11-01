package com.studyhub.setup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.studyhub.setup.service.DatabaseConnectionService;

@Controller
public class SetupController {
    
    private final DatabaseConnectionService databaseConnectionService;

    public SetupController(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @GetMapping("/setup")
    public String setupStatus(Model model) {
        model.addAttribute("connection", databaseConnectionService.checkConnection());
        return "setup/status";
    }
}
