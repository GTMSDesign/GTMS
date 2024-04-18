package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.LoginLogsDao;
import com.nju.edu.gtms.model.vo.LoginLogsVO;
import com.nju.edu.gtms.service.LoginLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogsServiceImpl implements LoginLogsService {
    private final LoginLogsDao loginLogsDao;

    @Autowired
    public LoginLogsServiceImpl(LoginLogsDao loginLogsDao){
        this.loginLogsDao=loginLogsDao;
    }


    @Override
    public List<LoginLogsVO> getAllLoginLogs(){
        return loginLogsDao.getAllLoginLogs();
    }

    @Override
    public void insertLoginLogs(String userAccount, String ipAddress) {
        loginLogsDao.insertLoginLogs(userAccount,ipAddress);
    }
}
