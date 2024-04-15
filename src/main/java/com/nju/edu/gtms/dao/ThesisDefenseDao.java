package com.nju.edu.gtms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ThesisDefenseDao {
    void fileUpload(String defense_id, String type, String url);

    String fileDownload(String id, String type);
}
