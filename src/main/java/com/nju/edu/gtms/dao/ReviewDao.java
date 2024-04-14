package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.model.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewDao {
    List<ReviewVO> getThesisByReviewerId(String teacherId);

}
