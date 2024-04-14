package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.StudentPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherDao {
    TeacherPO findOneById(String teacherId);

    void updatePhoneByTeacherId(String phone,String teacherId);

    void updateEmailByTeacherId(String email,String teacherId);
}
