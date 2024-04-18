package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.vo.LoginLogsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface LoginLogsDao {
    List<LoginLogsVO> getAllLoginLogs();
}
