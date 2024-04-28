package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.util.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public interface RegistrarService {
    List<AccountPO> getUnenteredStudents();
    List<AccountPO> getUnenteredTeachers();
    void submitStudentPO(StudentPO studentPO);

    void submitTeacherPO(TeacherPO teacherPO);

    void submitPlagiarismCheck(String studentId, String thesisId, String repetition, String conclusion);

    List<TeacherPO> getInternalTeachers();

    List<TeacherPO> getExternalTeachers();

    List<TeacherPO> getAllTeacher();

    List<StudentPO> getAllStudent();

    void submitAssignment(String studentId, String teacherId);

    void deleteAssignment(String studentId, String teacherId);

    String generateEvaluation(String thesisId, String studentId);

    void assignDefense(String thesis, String secretaryId, String teacherId1, String teacherId2, String teacherId3, String place, Date deadline);
}
