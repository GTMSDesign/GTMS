package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.AccountDao;
import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.dao.TeacherDao;
import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.service.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrarServiceImpl implements RegistrarService {
    private final AccountDao accountDao;

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;
    @Autowired
    public RegistrarServiceImpl(AccountDao accountDao,StudentDao studentDao,TeacherDao teacherDao){
        this.accountDao = accountDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
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
}
