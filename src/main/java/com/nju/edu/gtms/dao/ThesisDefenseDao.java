package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.ThesisDefensePO;
import com.nju.edu.gtms.model.po.ThesisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ThesisDefenseDao {
    void fileUpload(String defense_id, String type, String url);

    String fileDownload(String id, String type);

    ThesisDefensePO findDefenseByThesisId(String thesisId);

    void saveInformation(ThesisDefensePO thesisDefensePO);

    List<ThesisDefensePO> findThesisByTeacherId(String teacherId);
}
