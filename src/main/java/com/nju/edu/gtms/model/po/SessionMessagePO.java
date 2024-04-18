package com.nju.edu.gtms.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionMessagePO {

    private String messageId;

    private String sessionId;

    private String title;

    private String remarks; // 文档附言

    private String messageUrl; // 附件地址url

    private Timestamp messageTime; // 日期

}
