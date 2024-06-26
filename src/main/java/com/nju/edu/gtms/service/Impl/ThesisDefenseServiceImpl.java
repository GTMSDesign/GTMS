package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.ThesisDefenseDao;
import com.nju.edu.gtms.model.po.ThesisDefensePO;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.model.vo.DefensedThesisVO;
import com.nju.edu.gtms.model.vo.ThesisDefenseVO;
import com.nju.edu.gtms.service.EmailService;
import com.nju.edu.gtms.service.ThesisDefenseService;
import com.nju.edu.gtms.dao.ThesisDao;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {

    private final ThesisDefenseDao thesisDefenseDao;

    private final ThesisDao thesisDao;

    private final EmailService emailService;

    public ThesisDefenseServiceImpl(ThesisDefenseDao thesisDefenseDao, ThesisDao thesisDao, EmailService emailService) {
        this.thesisDefenseDao = thesisDefenseDao;
        this.thesisDao = thesisDao;
        this.emailService = emailService;
    }

    @Override
    public void saveInformation(ThesisDefenseVO thesisDefenseVO){

        ThesisDefensePO thesisDefensePO = new ThesisDefensePO() ;

        thesisDefensePO.setDefenseId(Integer.parseInt(thesisDefenseVO.getDefenseId()));

        thesisDefensePO.setConclusion(thesisDefenseVO.getState());

        thesisDefensePO.setReview(thesisDefenseVO.getReview());

        thesisDefensePO.setDefenseRemarks(thesisDefenseVO.getDefenseRemarks());

        thesisDefensePO.setDefenseUrl(thesisDefenseVO.getDefenseUrl());

        thesisDefenseDao.saveInformation(thesisDefensePO);


    }

    @Override
    public ThesisDefensePO findDefenseByThesisId(String thesisId){
        return thesisDefenseDao.findDefenseByThesisId(thesisId);
    }

    @Override
    public void formalSubmission(String defenseId) {
        ThesisDefensePO thesisDefensePO = thesisDefenseDao.findDefenseByDefenseId(defenseId);
        String conclusion = thesisDefensePO.getConclusion();
        int thesisId = thesisDefensePO.getThesisId();
        ThesisPO thesisPO = thesisDao.findOneByThesisId(String.valueOf(thesisId));
        if(conclusion.equals("pass")){
            //thesisDefenseDao.deleteDefense(defenseId);
            thesisDefenseDao.changeStatueToFinishDefense(thesisId);
        } else if (conclusion.equals("fail") ) {
            System.out.println("todelete");
            thesisDefenseDao.deleteDefense(defenseId);
            thesisDefenseDao.changeStatueToFinishDraft(thesisId);
            int defenseTime = thesisPO.getDefenseTimes();
            defenseTime += 1;
            thesisDao.addDefense(defenseTime, String.valueOf(thesisId));

        }
        else {
            String subject = "论文答辩结果通知";
            String body = String.format("尊敬的%s老师和%s同学，你们的论文答辩结果为暂缓通过， 请在规定的时间内完成相应的修改。",
                    thesisPO.getTeacherName(), thesisPO.getStudentName());
            // 向学生发送邮件
            emailService.send(thesisPO.getStudentId(),subject,body);
            // 向教师发送邮件
            emailService.send(thesisPO.getTeacherId(), subject, body);
        }


    }

    @Override
    public List<ThesisDefensePO> getDefenseThesisByTeacherId(String teacherId){
        return chooseStates(thesisDefenseDao.findDefenseThesisByTeacherId(teacherId));
    }

    @Override
    public List<ThesisDefensePO> getDefenseThesisByTeacher1Id(String teacherId){
        return chooseStates(thesisDefenseDao.findDefenseThesisByTeacher1Id(teacherId));
    }

    @Override
    public List<ThesisDefensePO> getAllDefenseThesisByTeachersId(String teacherId) {
        List<ThesisDefensePO> allDefenseThesisByTeachersId = new ArrayList<>();
        List<ThesisDefensePO> allDeferredDefense = new ArrayList<>();
        allDefenseThesisByTeachersId.addAll(thesisDefenseDao.findDefenseThesisByTeacherId(teacherId));
        allDefenseThesisByTeachersId.addAll(thesisDefenseDao.findDefenseThesisByTeacher1Id(teacherId));
        allDefenseThesisByTeachersId.addAll(thesisDefenseDao.findDefenseThesisByTeacher2Id(teacherId));
        allDefenseThesisByTeachersId.addAll(thesisDefenseDao.findDefenseThesisByTeacher3Id(teacherId));
        for(ThesisDefensePO thesisDefensePO: allDefenseThesisByTeachersId){
            if (thesisDefensePO.getConclusion().equals("delay")){
                allDeferredDefense.add(thesisDefensePO);
            }
        }
        return chooseStates(allDeferredDefense);
    }

    @Override
    public ThesisDefensePO getDefenseByDefenseId(String defenseId){
//        System.out.println("thesisDefenseDao_find_defensebydefenseid");
//        System.out.println(thesisDefenseDao.findDefenseByDefenseId(defenseId));
        return thesisDefenseDao.findDefenseByDefenseId(defenseId);
    }

    @Override
    public DefensedThesisVO getDefensedThesis(String defenseId) {

        ThesisDefensePO thesisDefensePO = thesisDefenseDao.findDefenseByDefenseId(defenseId) ;

        System.out.println(thesisDefensePO);

        ThesisPO thesisPO = thesisDao.findOneByThesisId(String.valueOf(thesisDefensePO.getThesisId()));

        DefensedThesisVO defensedThesisVO = new DefensedThesisVO();

        defensedThesisVO.setStudentId(thesisPO.getStudentId());

        defensedThesisVO.setThesisId(thesisPO.getThesisId());

        defensedThesisVO.setStudentName(thesisPO.getStudentName());

        defensedThesisVO.setTeacherId(thesisPO.getTeacherId());

        defensedThesisVO.setTeacherName(thesisPO.getTeacherName());

        defensedThesisVO.setTitle(thesisPO.getTitle());

        defensedThesisVO.setReview(thesisDefensePO.getReview());

        defensedThesisVO.setStatus(thesisPO.getStatus());

        defensedThesisVO.setConclusion(thesisDefensePO.getConclusion());

        defensedThesisVO.setDefenseRemarks(thesisDefensePO.getDefenseRemarks());

        defensedThesisVO.setThesisUrl(thesisPO.getThesisUrl());

        defensedThesisVO.setDefenseUrl(thesisDefensePO.getDefenseUrl());

        return defensedThesisVO;

    }

    @Override
    public void saveDeferredInformation(ThesisDefenseVO thesisDefenseVO){

        String defenseId = thesisDefenseVO.getDefenseId();

        ThesisDefensePO thesisDefensePO = thesisDefenseDao.findDefenseByDefenseId(defenseId);

        String thesisId = String.valueOf(thesisDefensePO.getThesisId());

        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesisId);

        System.out.println(thesisDefenseVO.getState());
        if (thesisDefenseVO.getState().equals("pass")){
            System.out.println(thesisDefensePO.getConclusion());
            if(thesisDefensePO.getConclusion().contains("delay")){
//                System.out.println("delaydelay");
                thesisDefensePO.setConclusion("");
            }
            thesisDefenseVO.setState(thesisDefenseVO.getState() + thesisDefensePO.getConclusion());
            saveInformation(thesisDefenseVO);
            if (thesisDefenseVO.getState().equals("passpasspass")){
//                thesisDefenseDao.deleteDefense(defenseId);
                thesisDefenseDao.changeStatueToFinishDefense(Integer.parseInt(thesisId));
            }
        } else{
            thesisDefenseDao.deleteDefense(defenseId);
            thesisDefenseDao.changeStatueToFinishDraft(Integer.parseInt(thesisId));
            int defenseTime = thesisPO.getDefenseTimes();
            defenseTime += 1;
            thesisDao.addDefense(defenseTime,thesisId);

        }


    }

    public List<ThesisDefensePO> chooseStates (List<ThesisDefensePO> thesisDefensePOs) {
        List<ThesisDefensePO> thesisDefensePOS = new ArrayList<>();
        for (ThesisDefensePO thesisDefensePO : thesisDefensePOs) {
            ThesisPO thesisPO = thesisDao.findOneByThesisId(String.valueOf(thesisDefensePO.getThesisId()));
            if (thesisPO.getStatus().equals("答辩中")) {
                thesisDefensePOS.add(thesisDefensePO);
            }
        }
        return thesisDefensePOS;
    }
}
