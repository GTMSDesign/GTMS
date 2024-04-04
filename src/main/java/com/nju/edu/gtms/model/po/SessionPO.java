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
public class SessionPO {

    private String sessionId;

    private String teacherId;

    private String studentId;

    private String title;

    private String remarks; // 文档附言

    private String url; // 附件地址url

    private Date date; // 日期

}
