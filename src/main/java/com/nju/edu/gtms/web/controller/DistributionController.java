package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.vo.FileTransferVO;
import com.nju.edu.gtms.service.DistributionService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/distribution")
public class DistributionController {
    private final DistributionService distributionService;

    @Autowired
    public DistributionController(DistributionService distributionService){
        this.distributionService = distributionService;
    }

    @GetMapping("/getStudentByTeacherId")
    public Result getStudentByTeacherId(@RequestParam("teacherId") String teacherId){
        return Result.success(distributionService.getStudentByTeacherId(teacherId));
    }

    @GetMapping("/getTeacherByStudentId")
    public Result getTeacherByStudentId(@RequestParam("studentId") String studentId){
        return Result.success(distributionService.getTeacherByStudentId(studentId));
    }
}
