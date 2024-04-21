package com.nju.edu.gtms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DistributionDao {
    String[] getStudentIdByTeacherId(String teacherId);
    String[] getTeacherIdByStudentId(String studentId);

    void submitAssignment(String studentId, String teacherId);

    void deleteAssignment(String studentId, String teacherId);
}
