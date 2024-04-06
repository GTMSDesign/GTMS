package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.vo.AccountVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AccountService {

    Map<String, String> login(AccountVO accountVO);

}
