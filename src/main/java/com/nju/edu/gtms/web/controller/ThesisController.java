package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.service.AccountService;
import com.nju.edu.gtms.service.ThesisService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/thesis")
public class ThesisController {
    private final ThesisService thesisService;
    @Autowired
    public ThesisController(ThesisService thesisService){
        this.thesisService = thesisService;
    }


    @GetMapping("/getThesisByTeacherId")
    public Result getThesisByTeacherId(@RequestParam("account") String teacherId){
        return Result.success(thesisService.getThesisByTeacherId(teacherId));
    }

    @GetMapping("/getThesisByTeacherIdAndStatus")
    public Result getThesisByTeacherIdAndStatus(@RequestParam("account") String teacherId,@RequestParam("status")String status){
        return Result.success(thesisService.getThesisByTeacherIdAndStatus(teacherId,status));
    }
}
