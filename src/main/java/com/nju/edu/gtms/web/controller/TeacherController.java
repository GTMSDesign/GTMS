package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.service.TeacherService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {
    private final TeacherService teacherService;;

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
}
