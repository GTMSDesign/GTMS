package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.ThesisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ThesisDao {
    List<ThesisPO> findThesisByTeacherId(String teacherId);

    List<ThesisPO> findThesisByTeacherIdAndStatus(String teacherId,String status);
}
