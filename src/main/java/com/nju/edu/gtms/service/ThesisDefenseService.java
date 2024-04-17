package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.po.ThesisDefensePO;
import com.nju.edu.gtms.model.vo.ThesisDefenseVO;

public interface ThesisDefenseService {

    void saveInformation(ThesisDefenseVO thesisDefenseVO);

    ThesisDefensePO findDefenseByThesisId(String thesisId);
}
