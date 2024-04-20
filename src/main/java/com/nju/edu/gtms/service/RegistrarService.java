package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface RegistrarService {
    List<AccountPO> getUnenteredStudents();
    List<AccountPO> getUnenteredTeachers();
    void submitStudentPO(StudentPO studentPO);

    void submitTeacherPO(TeacherPO teacherPO);

    void submitPlagiarismCheck(String studentId, String thesisId, String repetition, String conclusion);
}
