package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.ThesisDefensePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ThesisDefenseDao {

    void saveInformation(ThesisDefensePO thesisDefensePO)

    ;
}
