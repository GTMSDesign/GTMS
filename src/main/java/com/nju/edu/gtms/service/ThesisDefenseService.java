package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.ThesisDefensePO;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.model.vo.ThesisDefenseVO;

import java.util.List;

public interface ThesisDefenseService {
    List<ThesisDefensePO> getThesisByTeacherId(String teacherId);

    void saveInformation(ThesisDefenseVO thesisDefenseVO);

    ThesisDefensePO findDefenseByThesisId(String thesisId);
}
