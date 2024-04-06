package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountVO {

    private String account;

    private String password;

    private String name;

    private String power; // 用户权力（老师、学生、管理员、教务员）
}
