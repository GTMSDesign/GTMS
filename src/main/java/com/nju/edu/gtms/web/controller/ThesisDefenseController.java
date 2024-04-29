package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.vo.ThesisDefenseVO;
import com.nju.edu.gtms.service.ThesisDefenseService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/thesisDefense")
public class ThesisDefenseController {

    private final ThesisDefenseService thesisDefenseService;
    @Autowired
    public ThesisDefenseController(ThesisDefenseService thesisDefenseService){
            this.thesisDefenseService = thesisDefenseService;
    }

    @PostMapping("/preliminaryResolution")
    public Result saveInformation( ThesisDefenseVO thesisDefenseVO){
        thesisDefenseService.saveInformation(thesisDefenseVO);
        return Result.success();
    }
    @GetMapping("/getThesisByTeacherId")
    public Result getThesisByTeacherId(@RequestParam("account") String teacherId){
        System.out.println(thesisDefenseService.getDefenseThesisByTeacherId(teacherId));
        return Result.success(thesisDefenseService.getDefenseThesisByTeacherId(teacherId));
    }

    @GetMapping("/getThesisByTeacher1Id")
    public Result getThesisByTeacher1Id(@RequestParam("account") String teacherId){
        System.out.println(thesisDefenseService.getDefenseThesisByTeacher1Id(teacherId));
        return Result.success(thesisDefenseService.getDefenseThesisByTeacher1Id(teacherId));
    }

    @GetMapping("/getDefensedThesis")
    public Result getDefensedThesis(@RequestParam("defenseId") String defenseId){
//        System.out.println(thesisDefenseService.getDefenseThesisByTeacher1Id(teacherId));
        return Result.success(thesisDefenseService.getDefensedThesis(defenseId));
    }

    @GetMapping("/getDefenseByDefenseId")
    public Result getDefenseByDefenseId(@RequestParam("defenseId") String defenseId){
        return Result.success(thesisDefenseService.getDefenseByDefenseId(defenseId));
    }

    @GetMapping("/findDefenseByThesisId")
    public Result findDefenseByThesisId(@RequestParam("thesisId") String thesisId){
        System.out.println("111111111111");
        return Result.success(thesisDefenseService.findDefenseByThesisId(thesisId));
    }

    @PostMapping("/submission")
    public Result submitDefense(String defenseId){
        thesisDefenseService.formalSubmission(defenseId);
        System.out.println("getSubmission");
        return Result.success();
    }


}

