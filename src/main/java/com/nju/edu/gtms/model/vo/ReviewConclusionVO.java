package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewConclusionVO {
    private String title;

    private String thesisId;

    private String studentName;

    private String studentId;

    private String status;

    private int internalScore;

    private int externalScore;

    private Date date;
}
