package com.nju.edu.gtms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.nju.edu.gtms.model.po.AccountPO;

import java.util.List;

@Repository
@Mapper
public interface AccountDao {

    AccountPO findByAccountAndPassword(String account,String password);

    AccountPO findByAccount(String account);

    List<AccountPO> getUnenteredStudents();
}
