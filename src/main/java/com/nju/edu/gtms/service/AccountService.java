package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.User;
import com.nju.edu.gtms.model.vo.AccountVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AccountService extends UserDetailsService {
    String getUsernameByUserId(String userId);

}
