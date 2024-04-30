package com.nju.edu.gtms.dao;

import com.nju.edu.gtms.model.po.ThesisDefensePO;
import com.nju.edu.gtms.model.po.ThesisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ThesisDefenseDao {
    void fileUpload(String defense_id, String type, String url);

    String fileDownload(String id, String type);

    ThesisDefensePO findDefenseByThesisId(String thesisId);

    void saveInformation(ThesisDefensePO thesisDefensePO);

    List<ThesisDefensePO> findDefenseThesisByTeacherId(String teacherId);
    List<ThesisDefensePO> findDefenseThesisByTeacher1Id(String teacherId);
    List<ThesisDefensePO> findDefenseThesisByTeacher2Id(String teacherId);
    List<ThesisDefensePO> findDefenseThesisByTeacher3Id(String teacherId);
    ThesisDefensePO findDefenseByDefenseId(String defenseId);

    Date findLatestDefenseTimeByThesisId(String thesisId);

    String findReviewByThesisId(String thesisId);

    void assignDefense(String thesis, String secretaryId, String teacherId1, String teacherId2, String teacherId3, String place, Date deadline);
    void deleteDefense(String defenseId);

    void changeStatueToFinishDefense(int thesisId);
    void changeStatueToFinishDraft(int thesisId);
}
