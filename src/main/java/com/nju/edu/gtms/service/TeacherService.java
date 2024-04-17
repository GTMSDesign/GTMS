package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.TeacherPO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface TeacherService {

    TeacherPO getOneById(String teacherId);

    void reviewProposal(String thesisId,  String result);

    void updatePhoneByTeacherId(String phone,String teacherId);

    void updateEmailByTeacherId(String email,String teacherId);

    void approveDraft(String thesisId);

    void approveDefence(String thesisId);

    void approveDeffer(String thesisId);

    void defenseResolution(String thesisId);
}
