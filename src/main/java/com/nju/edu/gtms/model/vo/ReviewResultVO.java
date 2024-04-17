package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.PrimitiveIterator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResultVO {
    private String thesisId;

    private int score;

    private String teacherId;

    private String comment;

    private String advice;

}
