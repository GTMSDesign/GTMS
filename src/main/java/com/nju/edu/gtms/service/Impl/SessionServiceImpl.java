package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.SessionDao;
import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.model.po.SessionMessagePO;
import com.nju.edu.gtms.model.po.SessionPO;
import com.nju.edu.gtms.model.vo.SessionMessageVO;
import com.nju.edu.gtms.model.vo.SessionVO;
import com.nju.edu.gtms.service.SessionService;
import com.nju.edu.gtms.util.InsertedKeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionDao sessionDao;

    @Autowired
    public SessionServiceImpl(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }


    @Override
    public String submitSessionVO(SessionVO sessionVO) {
        InsertedKeyHolder insertedKeyHolder = new InsertedKeyHolder();
        sessionDao.submitSessionVO(sessionVO, insertedKeyHolder);
        String newSessionId;
        newSessionId = String.valueOf(insertedKeyHolder.getInsertedId());
        return newSessionId;
    }

    @Override
    public String submitSessionMessageVO(SessionMessageVO sessionMessageVO) {
        InsertedKeyHolder insertedKeyHolder = new InsertedKeyHolder();
        sessionDao.submitSessionMessageVO(sessionMessageVO, insertedKeyHolder);
        String newSessionMessageId;
        newSessionMessageId = String.valueOf(insertedKeyHolder.getInsertedId());
        return newSessionMessageId;
    }

    @Override
    public List<SessionPO> getSessionByTeacherId(String teacherId) {
        return sessionDao.getSessionByTeacherId(teacherId);
    }

    @Override
    public List<SessionPO> getSessionByStudentId(String studentId) {
        return sessionDao.getSessionByStudentId(studentId);
    }

    @Override
    public List<SessionMessagePO> getSessionMessage(String sessionId) {
        return sessionDao.getSessionMessage(sessionId);
    }
}
