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
import java.util.Map;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    @Autowired
    public AccountServiceImpl(AccountDao accountDao){
        this.accountDao = accountDao;
    }


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


}
