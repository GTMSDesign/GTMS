package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherDao {
    TeacherPO findOneById(String teacherId);

    void updatePhoneByTeacherId(String phone,String teacherId);

    void updateEmailByTeacherId(String email,String teacherId);

    void insertTeacherPO(TeacherPO teacherPO);
    String getTeacherNameById(String teacherId);
    List<TeacherPO> getInternalTeachers();

    List<TeacherPO> getExternalTeachers();

    List<TeacherPO> getAllTeacher();

    void finishDelete(String thesisId);

}
