package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.ReviewDao;
import com.nju.edu.gtms.dao.TeacherDao;
import com.nju.edu.gtms.dao.ThesisDao;
import com.nju.edu.gtms.model.po.ReviewPO;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.model.vo.*;
import com.nju.edu.gtms.service.EmailService;
import com.nju.edu.gtms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    private final ThesisDao thesisDao;

    private final EmailService emailService;

    private final TeacherDao teacherDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao,ThesisDao thesisDao,EmailService emailService,TeacherDao teacherDao){
        this.thesisDao = thesisDao;
        this.reviewDao = reviewDao;
        this.emailService = emailService;
        this.teacherDao = teacherDao;
    }

    @Override
    public List<ReviewVO> getThesisByReviewerId(String teacherId) {
        return reviewDao.getThesisByReviewerId(teacherId);
    }

    @Override
    public ReviewMessageVO getReviewByThesisId(String thesisId) {
        return reviewDao.getReviewMessageByThesisId(thesisId);
    }

    @Override
    public List<ReviewRuleVO> getReviewRules() {
        return reviewDao.getRules();
    }

    @Override
    public void updateReviewRule(List<ReviewRuleVO> list) {
        reviewDao.resetRule();
        for(ReviewRuleVO ruleVO:list){
            reviewDao.insertRule(ruleVO);
        }
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

    @Override
    public List<ReviewConclusionVO> getReviewConclusionByTeacherId(String teacherId) {
        return reviewDao.getReviewConclusion(teacherId);
    }

    @Override
    public void assignReview(String thesisId, String internalId, String externalId, Date deadline) {
        reviewDao.deleteReviewByThesisId(thesisId);
        ReviewPO reviewPO = ReviewPO.builder()
                .thesisId(thesisId)
                .internalTeacherId(internalId)
                .externalTeacherId(externalId)
                .date(deadline)
                .externalScore(-1)
                .internalScore(-1)
                .build();
        reviewDao.insertReview(reviewPO);
        thesisDao.setThesisStatue("待评审",thesisId);
        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesisId);
        TeacherPO teacherPO = teacherDao.findOneById(internalId);
        String subject = "评审：" + thesisPO.getStudentName() + "_" + thesisPO.getTitle();
        String body1 = "尊敬的"+ teacherPO.getTeacherName()+"老师，您好！请您评审论文，并在截止时间之前提交评审意见。评审链接为http://localhost:5173/reviewManagement。";
        teacherPO = teacherDao.findOneById(externalId);
        String body2 = "尊敬的"+ teacherPO.getTeacherName()+"老师，您好！请您评审论文，并在截止时间之前提交评审意见。评审链接为http://localhost:5173/reviewManagement。";

        emailService.send(internalId,subject,body1);
        emailService.send(externalId,subject,body2);
    }
}
