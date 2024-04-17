package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.vo.ReviewMessageVO;
import com.nju.edu.gtms.model.vo.ReviewResultVO;
import com.nju.edu.gtms.model.vo.ReviewRuleVO;
import com.nju.edu.gtms.model.vo.ReviewVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public interface ReviewService {

    List<ReviewVO> getThesisByReviewerId(String teacherId);

    ReviewMessageVO getReviewByThesisId(String thesisId);

    List<ReviewRuleVO> getReviewRules();

    void submitReview(ReviewResultVO resultVO);
}
