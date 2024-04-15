package com.nju.edu.gtms.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThesisDefensePO {
    private String defenseId;

    private String thesisId;

    private String secretary;

    private String teacher1; // 默认为答辩会主席

    private String teacher2;

    private String teacher3;

    private String conclusion; // 答辩结论（通过、暂缓通过、不通过）

    private String resolution; // 答辩决议（正式、暂缓）

    private String defenseRemarks; // 答辩附言

    private String defenseUrl; // 答辩附件url

    private String resolutionUrl; // 决议附件url

    private String place; // 答辩地点

    private Date date; // 答辩时间

    private String review; // 答辩三个一评价

    public void setDefenseId(String defenseId) {
        this.defenseId = defenseId;
    }
    public void setThesisId(String thesisId) {
        this.thesisId = thesisId;
    }
    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }
    public void setTeacher1(String teacher1) {
        this.teacher1 = teacher1;
    }
    public void setTeacher2(String teacher2) {
        this.teacher2 = teacher2;
    }
    public void setTeacher3(String teacher3) {
        this.teacher3 = teacher3;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    public String getDefenseRemarks() {
        return defenseRemarks;
    }
    public void setDefenseUrl(String defenseUrl) {
        this.defenseUrl = defenseUrl;
    }
    public void setResolutionUrl(String resolutionUrl) {
        this.resolutionUrl = resolutionUrl;
    }
    public void setPlace(String place) {
        this.place = place;
    }    public void setDate(Date date) {
        this.date = date;
    }
    public void setReview(String review) {
        this.review = review;
    }

}
