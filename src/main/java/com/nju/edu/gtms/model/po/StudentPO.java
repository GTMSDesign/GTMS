package com.nju.edu.gtms.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentPO {

    private String studentId;

    private String studentName;
    //班级
    private String grade;

    private String phone;

    private String officePhone;

    private String homePhone;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private String email1;

    private String email2;

    private String company;

    private String position;

    private int excellentCourses;

    private int goodCourses;

    private int fairCourses;

    private int passCourses;
    //是否分配
    private String distribute;



}
