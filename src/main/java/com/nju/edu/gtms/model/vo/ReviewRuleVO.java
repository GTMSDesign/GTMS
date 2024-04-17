package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRuleVO {
    private String reviewItem;

    private String reviewElement;

    private int maxScore;


}
