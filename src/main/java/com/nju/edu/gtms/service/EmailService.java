package com.nju.edu.gtms.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface EmailService {
    void send( String userId,String subject,String body);

    void sendAttachmentMail(String userId,String subject,String body, MultipartFile attach);
}
