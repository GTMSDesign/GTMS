package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.ReviewDao;
import com.nju.edu.gtms.dao.ThesisDao;
import com.nju.edu.gtms.model.po.ReviewPO;
import com.nju.edu.gtms.model.vo.ReviewMessageVO;
import com.nju.edu.gtms.model.vo.ReviewResultVO;
import com.nju.edu.gtms.model.vo.ReviewRuleVO;
import com.nju.edu.gtms.model.vo.ReviewVO;
import com.nju.edu.gtms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    private final ThesisDao thesisDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao,ThesisDao thesisDao){
        this.thesisDao = thesisDao;
        this.reviewDao = reviewDao;
    }

    @Override
    public List<ReviewVO> getThesisByReviewerId(String teacherId) {
        return reviewDao.getThesisByReviewerId(teacherId);
    }

    @Override
    public ReviewMessageVO getReviewByThesisId(String thesisId, String role) {
        return reviewDao.getReviewMessageByThesisId(thesisId);
    }

    @Override
    public List<ReviewRuleVO> getReviewRules() {
        return reviewDao.getRules();
    }

    @Override
    public void submitReview(ReviewResultVO resultVO) {
        ReviewPO reviewPO = reviewDao.getReviewByThesisId(resultVO.getThesisId());
        if (reviewPO==null){
            return;
        }else if(reviewPO.getExternalTeacherId().equals(resultVO.getTeacherId())&&reviewPO.getExternalScore()==-1){
            reviewPO.setExternalComment(resultVO.getComment());
            reviewPO.setExternalAdvice(resultVO.getAdvice());
            reviewPO.setExternalScore(resultVO.getScore());
        }else if(reviewPO.getInternalTeacherId().equals(resultVO.getTeacherId())&&reviewPO.getInternalScore()==-1){
            reviewPO.setInternalComment(resultVO.getComment());
            reviewPO.setInternalAdvice(resultVO.getAdvice());
            reviewPO.setInternalScore(resultVO.getScore());
        }
        reviewDao.updateReview(reviewPO);
        int score1 = reviewPO.getExternalScore();
        int score2 = reviewPO.getInternalScore();
        int sum = (score1+score2)/2;
        if(score1==-1||score2==-1){
            return;
        }
        if(score1<60||score2<60){
            thesisDao.setThesisStatue("已开题",resultVO.getThesisId());
        }else if(sum>=90){
            thesisDao.setThesisStatue("答辩前定稿",resultVO.getThesisId());
        }else if(sum>=75){
            thesisDao.setThesisStatue("待答辩",resultVO.getThesisId());
        }else {
            thesisDao.setThesisStatue("完成初稿",resultVO.getThesisId());
        }
    }
}
