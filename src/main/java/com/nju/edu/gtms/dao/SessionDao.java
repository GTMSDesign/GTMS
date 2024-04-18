package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.SessionMessagePO;
import com.nju.edu.gtms.model.po.SessionPO;
import com.nju.edu.gtms.model.vo.SessionMessageVO;
import com.nju.edu.gtms.model.vo.SessionVO;
import com.nju.edu.gtms.util.InsertedKeyHolder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SessionDao {
    void fileUpload(String session_id, String type, String url);
    String fileDownload(String id, String type);
    void submitSessionVO(SessionVO sessionVO,InsertedKeyHolder insertedKeyHolder);
    void  submitSessionMessageVO(SessionMessageVO sessionMessageVO);
    List<SessionPO> getSessionByTeacherId(String teacherId);
    List<SessionMessagePO> getSessionMessage(String sessionId);
}
