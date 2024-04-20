package com.nju.edu.gtms.service;
import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistributionService {
    List<StudentPO> getStudentByTeacherId(String teacherId);
    List<TeacherPO> getTeacherByStudentId(String studentId);
}
