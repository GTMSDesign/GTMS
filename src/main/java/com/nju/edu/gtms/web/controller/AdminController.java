package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.service.LoginLogsService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    private final LoginLogsService loginLogsService;

    @Autowired
    public AdminController(LoginLogsService loginLogsService) {
        this.loginLogsService = loginLogsService;
    }

    @GetMapping("/getAllLoginLogs")
    public Result getAllLoginLogs(){
        return Result.success(loginLogsService.getAllLoginLogs());
    }
}
