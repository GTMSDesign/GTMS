package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.AccountDao;
import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.service.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrarServiceImpl implements RegistrarService {
    private final AccountDao accountDao;

    private final StudentDao studentDao;
    @Autowired
    public RegistrarServiceImpl(AccountDao accountDao,StudentDao studentDao){
        this.accountDao = accountDao;
        this.studentDao = studentDao;
    }


    @Override
    public List<AccountPO> getUnenteredStudents() {
        return accountDao.getUnenteredStudents();
    }

    @Override
    public void submitStudentPO(StudentPO studentPO) {
        StudentPO student = studentDao.findOneById(studentPO.getStudentId());
        if(student==null){
            studentDao.insertStudentPO(studentPO);
        }
    }
}
