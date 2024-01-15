package com.spring.mvc.starter.controller;

import com.spring.mvc.starter.service.BatchUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-batch-update")
public class BatchController {

    private final BatchUpdateService batchUpdateService;

    @Autowired
    public BatchController(BatchUpdateService batchUpdateService) {
        this.batchUpdateService = batchUpdateService;
    }

    @GetMapping()
    public String menu() {
        return "batch/index";
    }

    @GetMapping("/simple")
    public String simpleUpdate() {
        batchUpdateService.simpleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/batch")
    public String batchUpdate() {
        batchUpdateService.batchUpdate();
        return "redirect:/people";
    }
}
