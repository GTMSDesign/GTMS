package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.TeacherDao;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao){
        this.teacherDao = teacherDao;
    }

    @Override
    public TeacherPO getOneById(String teacherId) {
        return teacherDao.findOneById(teacherId);
    }
}
