package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.ThesisPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThesisService{
    List<ThesisPO> getThesisByTeacherId(String teacherId);

    List<ThesisPO> getThesisByStudentId(String studentId);

    List<ThesisPO> getThesisByTeacherIdAndStatus(String teacherId,String status);

    ThesisPO getThesisByThesisId(String thesisId);

    List<ThesisPO> getAllThesis();

    List<ThesisPO> getThesisByStatus(String status);
}
