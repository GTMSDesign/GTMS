package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.ThesisDefenseDao;
import com.nju.edu.gtms.model.po.ThesisDefensePO;
import com.nju.edu.gtms.model.vo.DefensedThesisVO;
import com.nju.edu.gtms.model.vo.ThesisDefenseVO;
import com.nju.edu.gtms.service.ThesisDefenseService;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.dao.ThesisDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {

    private final ThesisDefenseDao thesisDefenseDao;

    private final ThesisDao thesisDao;

    public ThesisDefenseServiceImpl(ThesisDefenseDao thesisDefenseDao, ThesisDao thesisDao) {
        this.thesisDefenseDao = thesisDefenseDao;
        this.thesisDao = thesisDao;
    }

    @Override
    public void saveInformation(ThesisDefenseVO thesisDefenseVO){

        ThesisDefensePO thesisDefensePO = new ThesisDefensePO() ;

        thesisDefensePO.setThesisId(Integer.parseInt(thesisDefenseVO.getThesisId()));

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
        System.out.println(conclusion);
        System.out.println(thesisId);

        if(conclusion.equals("pass")){
            thesisDefenseDao.deleteDefense(defenseId);
            thesisDefenseDao.changeStatueToFinishDefense(thesisId);
        } else if (conclusion.equals("fail") ) {
            System.out.println("todelete");
            thesisDefenseDao.deleteDefense(defenseId);
            thesisDefenseDao.changeStatueToFinishDraft(thesisId);
        }
        // todo:暂缓通过论文此处是否需要更改状态
//        else {
//            thesisDefenseDao.changeStatue
//        }

    }

    @Override
    public List<ThesisDefensePO> getDefenseThesisByTeacherId(String teacherId){
        System.out.println("thesisDefenseDao_find_success");
        return thesisDefenseDao.findDefenseThesisByTeacherId(teacherId);
    }

    @Override
    public List<ThesisDefensePO> getDefenseThesisByTeacher1Id(String teacherId){
        return thesisDefenseDao.findDefenseThesisByTeacher1Id(teacherId);
    }

    @Override
    public ThesisDefensePO getDefenseByDefenseId(String defenseId){
        System.out.println("thesisDefenseDao_find_defensebydefenseid");
        System.out.println(thesisDefenseDao.findDefenseByDefenseId(defenseId));
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

        System.out.println(defensedThesisVO);

        return defensedThesisVO;

    }
}
