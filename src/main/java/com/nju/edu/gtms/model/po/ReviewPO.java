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
public class ReviewPO {
    private String reviewId;

    private String thesisId;

    private String internalTeacherId;

    private String internalComment; // 内审老师对学生意见

    private String internalAdvice; // 内审老师对导师意见

    private int internalScore; // 内审老师打分

    private String externalTeacherId;

    private String externalComment; // 外审老师对学生意见

    private String externalAdvice; // 外审老师对导师意见

    private int externalScore; // 外审结论状态（未评审、通过、不通过）

    private Date date; // 评审时间
}
