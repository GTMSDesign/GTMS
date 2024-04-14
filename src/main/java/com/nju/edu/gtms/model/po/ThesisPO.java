package com.nju.edu.gtms.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThesisPO {

    private String thesisId;

    private String studentId;

    private String studentName;

    private String teacherId;

    private String teacherName;

    private String title;
    // 三个一评价200字以内
    private String comment;
    //导师意见
    private String opinion;
    //导师意见附件
    private String opinionUrl;
    // 论文状态
    private String status;
    //论文地址
    private String thesisUrl;
    //答辩次数
    private int defenseTimes;

    private String proposalUrl;
}
