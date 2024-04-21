package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.*;
import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.PlagiarismCheckPO;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.service.EmailService;
import com.nju.edu.gtms.service.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegistrarServiceImpl implements RegistrarService {
    private final AccountDao accountDao;

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;

    private final EmailService emailService;

    private final ThesisDao thesisDao;

    private final DistributionDao distributionDao;
    @Autowired
    public RegistrarServiceImpl(AccountDao accountDao, StudentDao studentDao, TeacherDao teacherDao, EmailService emailService, ThesisDao thesisDao, DistributionDao distributionDao){
        this.accountDao = accountDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.emailService = emailService;
        this.thesisDao = thesisDao;
        this.distributionDao = distributionDao;
    }


    @Override
    public List<AccountPO> getUnenteredStudents() {
        return accountDao.getUnenteredStudents();
    }

    @Override
    public List<AccountPO> getUnenteredTeachers() {
        return accountDao.getUnenteredTeachers();
    }

    @Override
    public void submitStudentPO(StudentPO studentPO) {
        StudentPO student = studentDao.findOneById(studentPO.getStudentId());
        if(student==null){
            studentDao.insertStudentPO(studentPO);
        }
    }

    @Override
    public void submitTeacherPO(TeacherPO teacherPO) {
        TeacherPO teacher = teacherDao.findOneById(teacherPO.getTeacherId());
        if(teacher==null){
            teacherDao.insertTeacherPO(teacherPO);
        }
    }

    @Override
    public void submitPlagiarismCheck(String studentId, String thesisId, String repetition, String conclusion) {
        if(conclusion.equals("不通过")){
            String subject = "论文重复检测率未通过";
            String body = "同学您好，您的论文重复检测率未通过，论文重复率为："+repetition+"%";
            emailService.send(studentId,subject,body);
            thesisDao.setThesisStatue("完成初稿",thesisId);
        }else {
            String subject = "论文重复检测率已通过";
            String body = "同学您好，您的论文重复检测率已通过";
            emailService.send(studentId,subject,body);
            thesisDao.setThesisStatue("通过重复率检测",thesisId);
        }
        PlagiarismCheckPO plagiarismCheckPO = PlagiarismCheckPO.builder()
                .conclusion(conclusion)
                .repetition(repetition)
                .thesisId(thesisId)
                .date(new Date())
                .build();
        thesisDao.addPlagiarismCheck(plagiarismCheckPO);
    }

    @Override
    public List<TeacherPO> getInternalTeachers() {
        return teacherDao.getInternalTeachers();
    }

    @Override
    public List<TeacherPO> getExternalTeachers() {
        return teacherDao.getExternalTeachers();
    }



    @Override
    public List<TeacherPO> getAllTeacher() {
        return teacherDao.getAllTeacher();
    }

    @Override
    public List<StudentPO> getAllStudent() {
        return studentDao.getAllStudent();
    }

    @Override
    public void submitAssignment(String studentId, String teacherId) {
        studentDao.updateDistribution(studentId,"是");
        distributionDao.submitAssignment(studentId,teacherId);
    }

    @Override
    public void deleteAssignment(String studentId, String teacherId) {
        studentDao.updateDistribution(studentId,"否");
        distributionDao.deleteAssignment(studentId,teacherId);
    }


}
