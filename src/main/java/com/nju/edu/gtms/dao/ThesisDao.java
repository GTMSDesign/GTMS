package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.ThesisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ThesisDao {
    List<ThesisPO> findThesisByTeacherId(String teacherId);

    ThesisPO findOneByThesisId(String ThesisId);

    List<ThesisPO> findThesisByTeacherIdAndStatus(String teacherId,String status);

    void setThesisStatue(String status,String thesisId);

    void deleteOneByThesisId(String thesisId);

    void fileUpload(String thesis_id, String type, String url);

    String fileDownload(String id, String type);
}
