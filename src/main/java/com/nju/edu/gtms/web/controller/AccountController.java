package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.vo.AccountVO;
import com.nju.edu.gtms.service.AccountService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }


    @GetMapping("/getAllAccount")
    public Result getAllAccount(){
        return Result.success(accountService.getAllAccount());
    }

    @PostMapping("/updateByAccount")
    public Result updateByAccount(@RequestBody AccountPO accountPO){
        accountService.updateByAccount(accountPO);
        return Result.success();
    }

    @PostMapping("/createAccount")
    public Result createAccount(@RequestBody AccountPO accountPO){
        return Result.success(accountService.createAccount(accountPO));
    }

    @PostMapping("/deleteByAccount")
    public Result deleteByAccount(@RequestParam String account){
        accountService.deleteByAccount(account);
        return Result.success();
    }

}
