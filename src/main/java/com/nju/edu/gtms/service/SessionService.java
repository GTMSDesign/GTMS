package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.SessionMessagePO;
import com.nju.edu.gtms.model.po.SessionPO;
import com.nju.edu.gtms.model.vo.SessionMessageVO;
import com.nju.edu.gtms.model.vo.SessionVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SessionService {
    String submitSessionVO(SessionVO sessionVO);

    String submitSessionMessageVO(SessionMessageVO sessionMessageVO);

    List<SessionPO> getSessionByTeacherId(String teacherId);
    List<SessionMessagePO> getSessionMessage(String sessionId);
}
