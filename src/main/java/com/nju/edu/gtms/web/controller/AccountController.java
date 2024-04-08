package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.vo.AccountVO;
import com.nju.edu.gtms.service.AccountService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

//    @PostMapping("/login")
//    public Result login(@RequestBody AccountVO accountVO){
//        return Result.success(accountService.login(accountVO));
//    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
