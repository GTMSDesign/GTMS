package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.service.RegistrarService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/registrar")
public class RegistrarController {
    RegistrarService registrarService;

    @Autowired
    public RegistrarController(RegistrarService registrarService){
        this.registrarService = registrarService;
    }

    @GetMapping("/getUnenteredStudents")
    public Result getUnenteredStudents(){
        return Result.success(registrarService.getUnenteredStudents());
    }

    @PostMapping("/submitStudentPO")
    public Result submitStudentPO(@RequestBody StudentPO studentPO){
        registrarService.submitStudentPO(studentPO);
        return Result.success();
    }
}
