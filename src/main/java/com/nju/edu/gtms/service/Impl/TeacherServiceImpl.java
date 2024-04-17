package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.TeacherDao;
import com.nju.edu.gtms.dao.ThesisDao;
import com.nju.edu.gtms.model.po.TeacherPO;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherDao teacherDao;
    private final ThesisDao thesisDao;  // 注入ThesisDao
    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao,ThesisDao thesisDao){
        this.teacherDao = teacherDao;
        this.thesisDao = thesisDao;
    }

    @Override
    public TeacherPO getOneById(String teacherId) {
        return teacherDao.findOneById(teacherId);
    }

    @Override
    public void reviewProposal(String thesisId, String result) {
        //先根据thesis查到对应PO
        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesisId);
        if(thesisPO==null||!thesisPO.getStatus().equals("待开题")){
            return;
        }
        //看result，通过则修改状态，不通过则删除记录
        if(result.equals("通过")){
            thesisDao.setThesisStatue("已开题",thesisId);
        }else{
            thesisDao.deleteOneByThesisId(thesisId);
        }

    }
    @Override
    public void approveDraft(String thesisId) {
        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesisId);
        if(thesisPO==null||!thesisPO.getStatus().equals("完成初稿")){
            return;
        }
        thesisDao.setThesisStatue("初步定稿",thesisId);
    }

    @Override
    public void approveDefence(String thesisId) {
        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesisId);
        if(thesisPO==null||!thesisPO.getStatus().equals("待答辩")){
            return;
        }
        thesisDao.setThesisStatue("答辩前定稿",thesisId);
    }


    @Override
    public void approveDeffer(String thesisId) {
        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesisId);
        if(thesisPO==null||!thesisPO.getStatus().equals("暂缓通过")){
            return;
        }
        thesisDao.setThesisStatue("暂缓后定稿",thesisId);
    }

    @Override
    public void defenseResolution(String thesisId) {
        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesisId);
        if(thesisPO==null||!thesisPO.getStatus().equals("通过答辩")){
            return;
        }
        thesisDao.setThesisStatue("答辩后定稿",thesisId);
    }


    @Override
    public void updatePhoneByTeacherId(String phone,String teacherId){
        teacherDao.updatePhoneByTeacherId(phone,teacherId);
    }
    @Override
    public void updateEmailByTeacherId(String email,String teacherId){
        teacherDao.updateEmailByTeacherId(email,teacherId);
    }
}
