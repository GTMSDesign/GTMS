package com.nju.edu.gtms.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherPO {

    private String teacherId;

    private String teacherName;

    private String phone;

    private String officePhone;

    private String email;
    //职称
    private String title;
    //学历
    private String education;
    //类别，校内/校外
    private String category;


}
