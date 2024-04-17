package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.ReviewPO;
import com.nju.edu.gtms.model.vo.ReviewMessageVO;
import com.nju.edu.gtms.model.vo.ReviewRuleVO;
import com.nju.edu.gtms.model.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewDao {
    List<ReviewVO> getThesisByReviewerId(String teacherId);
    ReviewMessageVO getReviewMessageByThesisId(String thesisId);

    ReviewPO getReviewByThesisId(String thesisId);

    List<ReviewRuleVO> getRules();

    void updateReview(ReviewPO reviewPO);
}
