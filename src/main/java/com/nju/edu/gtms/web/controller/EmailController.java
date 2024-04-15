package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.service.EmailService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;


    @Autowired
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public Result send(
            @RequestParam String userId,
            @RequestParam String subject,
            @RequestParam String body) {
        emailService.send(userId,subject,body);
        return Result.success();
    }

    @PostMapping("/sendAttachmentMail")
    public Result sendAttachmentMail(
            @RequestParam String userId,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestPart MultipartFile attach){
        emailService.sendAttachmentMail(userId,subject,body,attach);
        return Result.success();
    }
}
