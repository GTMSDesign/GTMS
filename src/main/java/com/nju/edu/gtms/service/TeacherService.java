package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.TeacherPO;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    TeacherPO getOneById(String teacherId);
}
