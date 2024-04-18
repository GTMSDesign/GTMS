package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.StudentPO;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    StudentPO getStudentById(String studentId);
    String getStudentNameById(String studentId);

}
