package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.StudentPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrarService {
    List<AccountPO> getUnenteredStudents();

    void submitStudentPO(StudentPO studentPO);
}
