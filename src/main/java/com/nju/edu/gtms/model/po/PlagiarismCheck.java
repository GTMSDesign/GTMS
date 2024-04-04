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
public class PlagiarismCheck {

    private String checkId;

    private String thesisId;
    // 检测出的重复率
    private double repetition;
    // 结论状态（通过、不通过）
    private String conclusion;
    // 检测日期
    private Date date;
}
