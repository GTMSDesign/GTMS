package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefensedThesisVO {

    private String studentId;

    private String studentName;

    private String teacherId;

    private String teacherName;

    private String title;

    private String thesisId;

    private String review; // 三个一评价200字以内 对应thesis里面的comment

    private String status;  // 论文状态

    private String conclusion;  // 答辩当前状态/结论（通过、暂缓通过、不通过）

    private String defenseRemarks;  // 答辩附言

    private String thesisUrl;

    private String defenseUrl;

}
