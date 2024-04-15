package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.vo.ThesisDefenseVO;
import com.nju.edu.gtms.service.ThesisDefenseService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("path = /thesisDefense")
public class ThesisDefenseController {

    private final ThesisDefenseService thesisDefenseService;
    @Autowired
    public ThesisDefenseController(ThesisDefenseService thesisDefenseService){
            this.thesisDefenseService = thesisDefenseService;
        }

        @PostMapping("/preliminaryResolution")
        public Result saveInformation(@RequestParam ThesisDefenseVO thesisDefenseVO){
            System.out.println("yes");

            thesisDefenseService.saveInformation(thesisDefenseVO);
            return Result.success();
        }
    }

