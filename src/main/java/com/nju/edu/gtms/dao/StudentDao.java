package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.StudentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentDao {
    StudentPO findOneByNumber(String studentNumber);

}
