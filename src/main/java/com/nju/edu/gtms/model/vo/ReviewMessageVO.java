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

    private String externalToStudent;

    private String internalToStudent;

    private String externalToTeacher;

    private String internalToTeacher;
}
