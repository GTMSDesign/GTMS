package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.vo.LoginLogsVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginLogsService {

    List<LoginLogsVO> getAllLoginLogs();

    void insertLoginLogs(String userAccount,String ipAddress);
}
