package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.service.TeacherService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/getTeacherById")
    @PreAuthorize("hasRole('TEACHER')")
    public Result getTeacherById(@RequestParam String teacherId){
        return Result.success(teacherService.getOneById(teacherId));
    }

    @PostMapping("/reviewProposal")
    public Result reviewProposal(@RequestParam String thesisId,@RequestParam String result){
        teacherService.reviewProposal(thesisId,result);
        return Result.success();
    }
    @PostMapping("/approveDraft")
    public Result approveDraft(@RequestParam String thesisId){
        teacherService.approveDraft(thesisId);
        return Result.success();
    }
    @PostMapping("/approveDefence")
    public Result approveDefence(@RequestParam String thesisId){
        teacherService.approveDefence(thesisId);
        return Result.success();
    }

    @PostMapping("/approveDeffer")
    public Result approveDeffer(@RequestParam String thesisId){
        teacherService.approveDeffer(thesisId);
        return Result.success();
    }

    @PostMapping("/defenseResolution")
    public Result defenseResolution(@RequestParam String thesisId){
        teacherService.defenseResolution(thesisId);
        return Result.success();
    }


    @PostMapping("/updatePhone")
    public Result updatePhoneByTeacherId(@RequestParam("phone") String phone,@RequestParam("account") String teacherId ){
        teacherService.updatePhoneByTeacherId(phone,teacherId);
        return Result.success();
    }

    @PostMapping("/updateEmail")
    public Result updateEmailByTeacherId(@RequestParam("email") String email,@RequestParam("account") String teacherId ){
        teacherService.updateEmailByTeacherId(email,teacherId);
        return Result.success();
    }
}
