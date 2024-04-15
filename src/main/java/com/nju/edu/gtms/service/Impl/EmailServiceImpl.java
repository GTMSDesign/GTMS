package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.AccountDao;
import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.dao.TeacherDao;
import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl implements EmailService {
    private final AccountDao accountDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    @Value("${spring.mail.username}")
    private String account;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,AccountDao accountDao,StudentDao studentDao,TeacherDao teacherDao){
        this.javaMailSender = javaMailSender;
        this.accountDao = accountDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }

    @Override
    public void send(String userId, String subject, String body) {
        String address = getAddress(userId);
        if(address.isEmpty()){
            return;
        }
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(account);
        smm.setTo(address);
        smm.setSubject(subject);
        smm.setText(body);
        javaMailSender.send(smm);
    }

    @SneakyThrows
    @Override
    public void sendAttachmentMail(String userId, String subject, String body, MultipartFile attach) {
        String address = getAddress(userId);
        if(address.isEmpty()){
            return;
        }
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(account);
        mimeMessageHelper.setTo(new String[]{address});
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        //文件路径
        byte[] bytes = attach.getBytes();
        String name = attach.getOriginalFilename();
        if (name != null) {
            mimeMessageHelper.addAttachment(name, new ByteArrayResource(bytes));
        }
        javaMailSender.send(mimeMailMessage);
    }

    private String getAddress(String userId){
        String address = "";
        AccountPO accountPO = accountDao.findByAccount(userId);
        if(accountPO==null){
            return "";
        }else if(accountPO.getPower().equals("teacher")){
            address = teacherDao.findOneById(userId).getEmail();
        }else if(accountPO.getPower().equals("student")){
            address = studentDao.findOneById(userId).getEmail1();
        }
        return address;
    }
}
