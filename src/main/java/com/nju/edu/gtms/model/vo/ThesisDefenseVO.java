package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThesisDefenseVO {

    private String defenseId;

    private String state;  // 答辩当前状态/结论（通过、暂缓通过、不通过）

    private String defenseRemarks;

    private String defenseUrl;

    private String review;  // 三个一评价


}
