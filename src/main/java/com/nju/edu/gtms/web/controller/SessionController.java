package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.po.SessionMessagePO;
import com.nju.edu.gtms.model.po.SessionPO;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.vo.SessionMessageVO;
import com.nju.edu.gtms.model.vo.SessionVO;
import com.nju.edu.gtms.service.SessionService;
import com.nju.edu.gtms.service.StudentService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @PostMapping("/submitSessionVO")
    public Result submitSessionVO(@RequestBody SessionVO sessionVO){
        return Result.success(sessionService.submitSessionVO(sessionVO));
    }

    @PostMapping("/submitSessionMessageVO")
    public Result submitSessionMessageVO(@RequestBody SessionMessageVO sessionMessageVO){
        return Result.success(sessionService.submitSessionMessageVO(sessionMessageVO));
    }

    @GetMapping("/getSessionByTeacherId")
    public Result getSessionByTeacherId(@RequestParam("teacherId") String teacherId){
        return Result.success(sessionService.getSessionByTeacherId(teacherId));
    }

    @GetMapping("/getSessionMessage")
    public Result getSessionMessage(@RequestParam("sessionId") String sessionId){
        return Result.success(sessionService.getSessionMessage(sessionId));
    }
}
