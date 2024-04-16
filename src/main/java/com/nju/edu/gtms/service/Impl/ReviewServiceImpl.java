package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.ReviewDao;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.model.vo.ReviewMessageVO;
import com.nju.edu.gtms.model.vo.ReviewVO;
import com.nju.edu.gtms.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @Override
    public List<ReviewVO> getThesisByReviewerId(String teacherId) {
        return reviewDao.getThesisByReviewerId(teacherId);
    }

    @Override
    public ReviewMessageVO getReviewByThesisId(String thesisId, String role) {
        ReviewMessageVO reviewMessageVO = reviewDao.getReviewByThesisId(thesisId);
        if(role.equals("student")){
            reviewMessageVO.setExternalToTeacher("");
            reviewMessageVO.setInternalToTeacher("");
        }
        return reviewMessageVO;
    }
}
