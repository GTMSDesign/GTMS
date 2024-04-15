package com.nju.edu.gtms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SessionDao {
    void fileUpload(String session_id, String type, String url);
    String fileDownload(String id, String type);
}
