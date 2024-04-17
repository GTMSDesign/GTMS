package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.DistributionDao;
import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionServiceImpl implements DistributionService {
    private DistributionDao distributionDao;
    private StudentDao studentDao;

    @Autowired
    public DistributionServiceImpl(DistributionDao distributionDao, StudentDao studentDao){
        this.distributionDao = distributionDao;
        this.studentDao = studentDao;
    }


    @Override
    public List<StudentPO> getStudentByTeacherId(String teacherId) {
        String studentArr[];
        List<StudentPO> studentList = new ArrayList<>();
        studentArr = distributionDao.getStudentIdByTeacherId(teacherId);
        for(String studentId : studentArr) {
            studentList.add(studentDao.findOneById(studentId));
        }
        return studentList;
    }
}
