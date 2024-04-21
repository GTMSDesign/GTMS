package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.vo.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ReviewService {

    List<ReviewVO> getThesisByReviewerId(String teacherId);

    ReviewMessageVO getReviewByThesisId(String thesisId);

    List<ReviewRuleVO> getReviewRules();

    void updateReviewRule(List<ReviewRuleVO> list);

    void submitReview(ReviewResultVO resultVO);

    List<ReviewConclusionVO>getReviewConclusionByTeacherId(String teacherId);

    void assignReview( String thesisId, String internalId, String externalId,  Date deadline);

}
