package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.model.vo.ReviewRuleVO;
import com.nju.edu.gtms.model.vo.SessionVO;
import com.nju.edu.gtms.service.RegistrarService;
import com.nju.edu.gtms.service.ReviewService;
import com.nju.edu.gtms.service.ThesisService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @PostMapping("/submitPlagiarismCheck")
    public Result submitPlagiarismCheck(@RequestParam String studentId,@RequestParam String thesisId,@RequestParam String repetition,@RequestParam String conclusion){
        registrarService.submitPlagiarismCheck(studentId,thesisId,repetition,conclusion);
        return Result.success();
    }

    @GetMapping("/getAllTeacher")
    public Result getAllTeacher(){
        return Result.success(registrarService.getAllTeacher());
    }

    @GetMapping("/getAllStudent")
    public Result getAllStudent(){
        return Result.success(registrarService.getAllStudent());
    }

    @PostMapping("/submitAssignment")
    public Result submitAssignment(@RequestParam String studentId,@RequestParam String teacherId){
        registrarService.submitAssignment(studentId,teacherId);
        return Result.success();
    }
    @PostMapping("/deleteAssignment")
    public Result deleteAssignment(@RequestParam String studentId,@RequestParam String teacherId){
        registrarService.deleteAssignment(studentId,teacherId);
        return Result.success();
    }
    @GetMapping("/getInternalTeachers")
    public Result getInternalTeachers(){
        return Result.success(registrarService.getInternalTeachers());
    }

    @GetMapping("/getExternalTeachers")
    public Result getExternalTeachers(){
        return Result.success(registrarService.getExternalTeachers());
    }

    @PostMapping("/assignReview")
    public Result assignReview(@RequestParam String[] thesisId,@RequestParam String internalId,@RequestParam String externalId,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline){
        for(String thesis:thesisId){
            reviewService.assignReview(thesis,internalId,externalId,deadline);
        }
        return Result.success();
    }

    @PostMapping("/generateEvaluation")
    public Result generateEvaluation(@RequestParam String thesisId, @RequestParam String studentId){
        return Result.success(registrarService.generateEvaluation(thesisId, studentId));
    }
}
