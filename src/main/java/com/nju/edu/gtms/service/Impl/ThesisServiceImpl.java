package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.ThesisDao;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService {
    private final ThesisDao thesisDao;

    @Autowired
    public ThesisServiceImpl(ThesisDao thesisDao){this.thesisDao=thesisDao;}

    @Override
    public List<ThesisPO> getThesisByTeacherId(String teacherId){
        return thesisDao.findThesisByTeacherId(teacherId);
    }

    @Override
    public List<ThesisPO> getThesisByStudentId(String studentId){
        return thesisDao.findThesisByStudentId(studentId);
    }

    @Override
    public List<ThesisPO> getThesisByTeacherIdAndStatus(String teacherId, String status) {
        return thesisDao.findThesisByTeacherIdAndStatus(teacherId,status);
    }

    @Override
    public ThesisPO getThesisByThesisId(String thesisId) {
        return thesisDao.findOneByThesisId(thesisId);
    }

    @Override
    public List<ThesisPO> getAllThesis() {
        return thesisDao.findAllThesis();
    }

    @Override
    public List<ThesisPO> getThesisByStatus(String status) {
        return thesisDao.getThesisByStatus(status);
    }
}

