package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.service.StudentService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/student")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
            this.studentService = studentService;
    }

    @GetMapping("/getStudentById")
    public Result getStudentById(@RequestParam String studentId){
        return Result.success(studentService.getStudentById(studentId));
    }

    @GetMapping("/getStudentNameById")
    public Result getStudentNameById(@RequestParam("studentId") String studentId){
        return Result.success(studentService.getStudentNameById(studentId));
    }

    @GetMapping("/finishDraft")
    public Result finishDraft(@RequestParam("thesisId") String thesisId){
        studentService.finishDraft(thesisId);
        return Result.success();
    }

    @GetMapping("/addNewThesis")
    public Result addNewThesis(@RequestParam("studentId") String studentId, @RequestParam("title") String title){
        studentService.addNewThesis(studentId, title);
        return Result.success();
    }
}
