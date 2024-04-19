package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.AccountDao;
import com.nju.edu.gtms.model.po.AccountPO;
import com.nju.edu.gtms.model.po.User;
import com.nju.edu.gtms.model.vo.AccountVO;
import com.nju.edu.gtms.service.AccountService;
import com.nju.edu.gtms.util.JwtConfig;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    @Autowired
    public AccountServiceImpl(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    //此处的username实为userId
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountPO accountPO = accountDao.findByAccount(username);
        if(accountPO==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User user = new User();
        user.setUsername(accountPO.getAccount());
        user.setPassword(accountPO.getPassword());
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + accountPO.getPower().toUpperCase()));

        return user;
    }

    public String getUsernameByUserId(String userId){
        AccountPO accountPO = accountDao.findByAccount(userId);
        return accountPO.getName();
    }

    @Override
    public List<AccountPO> getAllAccount() {
        return accountDao.getAllAccount();
    }

    @Override
    public void updateByAccount(AccountPO accountPO) {
        accountDao.updateByAccount(accountPO);
    }

    @Override
    public String createAccount(AccountPO accountPO) {
        if (accountDao.findByAccount(accountPO.getAccount()) != null){
            return "创建失败！";
        }else {
            accountDao.createAccount(accountPO);
            return "创建成功";
        }
    }

    @Override
    public void deleteByAccount(String account) {
        accountDao.deleteByAccount(account);
    }

}
