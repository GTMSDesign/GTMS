package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.model.vo.ReviewRuleVO;
import com.nju.edu.gtms.service.RegistrarService;
import com.nju.edu.gtms.service.ReviewService;
import com.nju.edu.gtms.service.ThesisService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/registrar")
public class RegistrarController {
    RegistrarService registrarService;
    ReviewService reviewService;

    ThesisService thesisService;
    @Autowired
    public RegistrarController(RegistrarService registrarService,ReviewService reviewService,ThesisService thesisService){
        this.reviewService = reviewService;
        this.registrarService = registrarService;
        this.thesisService = thesisService;
    }

    @GetMapping("/getUnenteredStudents")
    public Result getUnenteredStudents(){
        return Result.success(registrarService.getUnenteredStudents());
    }
    @GetMapping("/getUnenteredTeachers")
    public Result getUnenteredTeachers(){
        return Result.success(registrarService.getUnenteredTeachers());
    }


    @PostMapping("/submitStudentPO")
    public Result submitStudentPO(@RequestBody StudentPO studentPO){
        registrarService.submitStudentPO(studentPO);
        return Result.success();
    }
    @PostMapping("/submitTeacherPO")
    public Result submitTeacherPO(@RequestBody TeacherPO teacherPO){
        registrarService.submitTeacherPO(teacherPO);
        return Result.success();
    }

    @PostMapping("/updateReviewRule")
    public Result updateReviewRule(@RequestBody List<ReviewRuleVO> list){
        reviewService.updateReviewRule(list);
        return Result.success();
    }

    @GetMapping("/getAllThesis")
    public Result getAllThesis(){
        return Result.success(thesisService.getAllThesis());
    }

}
