package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewMessageVO {

    private String internalComment;

    private String internalAdvice;

    private String externalComment;

    private String externalAdvice;
}
