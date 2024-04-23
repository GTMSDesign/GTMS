package com.nju.edu.gtms.util;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteModel extends BaseRowModel {

    @ExcelProperty(value = "论文序号", index = 0)
    private String thesisId;

    @ExcelProperty(value = "导师姓名", index = 1)
    private String teacherName;

    @ExcelProperty(value = "学生姓名", index = 2)
    private String studentName;

    @ExcelProperty(value = "学位", index = 3)
    private String degree;

    @ExcelProperty(value = "论文题目", index = 4)
    private String title;

    @ExcelProperty(value = "课程成绩优秀", index = 5)
    private int excellentCourses;

    @ExcelProperty(value = "课程成绩良好", index = 6)
    private int goodCourses;

    @ExcelProperty(value = "课程成绩中等", index = 7)
    private int fairCourses;

    @ExcelProperty(value = "课程成绩及格", index = 8)
    private int passCourses;

    @ExcelProperty(value = """
            三个一的具体体现：                                 \s
            1、一个工程背景的项目；
            2、采用一种新的技术；
            3、一个能实际运行的系统。""", index = 9)
    private String review;

    @ExcelProperty(value = "鉴定情况", index = 10)
    private String verification;

    @ExcelProperty(value = "答辩日期", index = 11)
    private Date date;
}
