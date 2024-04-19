package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.User;
import com.nju.edu.gtms.model.vo.AccountVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AccountService extends UserDetailsService {
    String getUsernameByUserId(String userId);

    List<AccountPO> getAllAccount();

    void updateByAccount(AccountPO accountPO);

    String createAccount(AccountPO accountPO);

    void deleteByAccount(String account);
}
