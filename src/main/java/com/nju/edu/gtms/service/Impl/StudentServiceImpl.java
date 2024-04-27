package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.DistributionDao;
import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.dao.TeacherDao;
import com.nju.edu.gtms.dao.ThesisDao;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.service.StudentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentServiceImpl implements StudentService {


    private  StudentDao studentDao;
    private DistributionDao distributionDao;
    private TeacherDao teacherDao;
    private ThesisDao thesisDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, DistributionDao distributionDao, TeacherDao teacherDao, ThesisDao thesisDao){
        this.studentDao = studentDao;
        this.distributionDao = distributionDao;
        this.teacherDao = teacherDao;
        this.thesisDao = thesisDao;
    }


    @Override
    public StudentPO getStudentById(String studentId) {
        return studentDao.findOneById(studentId);
    }

    @Override
    public String getStudentNameById(String studentId) {
        return studentDao.getStudentNameById(studentId);
    }

    @Override
    public void finishDraft(String thesisId) {
        studentDao.finishDraft(thesisId);
    }

    @Override
    public void addNewThesis(String studentId, String title) {
        String thesisId = null;
        String studentName = studentDao.getStudentNameById(studentId);
        String teacherId = distributionDao.getTeacherIdByStudentId(studentId)[0];
        String teacherName = teacherDao.getTeacherNameById(teacherId);
        String comment = null;
        String opinion = null;
        String opinionUrl = null;
        String status = "未开题";
        String thesisUrl = null;
        int defenseTimes = 0;
        String proposalUrl = null;

        ThesisPO thesisPO = new ThesisPO(thesisId, studentId, studentName, teacherId, teacherName, title, comment, opinion, opinionUrl, status, thesisUrl, defenseTimes, proposalUrl);

        thesisDao.addNewThesis(thesisPO);
    }
}
