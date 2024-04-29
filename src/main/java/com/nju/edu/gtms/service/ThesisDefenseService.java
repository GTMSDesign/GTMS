package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.ThesisDefensePO;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.model.vo.DefensedThesisVO;
import com.nju.edu.gtms.model.vo.ThesisDefenseVO;

import java.util.List;

public interface ThesisDefenseService {
    List<ThesisDefensePO> getDefenseThesisByTeacherId(String teacherId);

    List<ThesisDefensePO> getDefenseThesisByTeacher1Id(String teacherId);

    ThesisDefensePO getDefenseByDefenseId(String defenseId);

    DefensedThesisVO getDefensedThesis(String defenseId);
    void saveInformation(ThesisDefenseVO thesisDefenseVO);

    ThesisDefensePO findDefenseByThesisId(String thesisId);

    void formalSubmission(String defenseId);
}
