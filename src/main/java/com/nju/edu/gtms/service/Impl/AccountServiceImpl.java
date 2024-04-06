package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.AccountDao;
import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.vo.AccountVO;
import com.nju.edu.gtms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    @Override
    public Map<String, String> login(AccountVO accountVO) {
        AccountPO accountPO = accountDao.findByAccountAndPassword(accountVO.getAccount(),accountVO.getPassword());
        //todo:生成token返回


        return null;
    }
}
