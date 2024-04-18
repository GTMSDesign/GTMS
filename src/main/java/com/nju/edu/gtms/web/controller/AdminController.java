package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.service.LoginLogsService;
import com.nju.edu.gtms.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insertLoginLogs")
    public Result insertLoginLogs(HttpServletRequest request, @RequestParam("userAccount")String userAccount ){
        String ipAddress = request.getRemoteAddr();
        loginLogsService.insertLoginLogs(userAccount,ipAddress);
        return Result.success();
    }
}
