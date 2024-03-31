package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.service.StudentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentServiceImpl implements StudentService {


    private  StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }



}
