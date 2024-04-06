package com.nju.edu.gtms.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    public static String SUCCESS_CODE = "00000";
    public static String FAIL_CODE = "11111";
    public static String SUCCESS_MSG = "Success";
    public static String FAIL_MSG = "Fail";
    private String code;
    private String msg;
    private Object result;

    public Result(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public static Result success(Object result){
        return new Result(SUCCESS_CODE,SUCCESS_MSG,result);
    }
    public static Result success(){
        return new Result(SUCCESS_CODE,SUCCESS_MSG);
    }

    public static Result fail(){
        return new Result(FAIL_CODE,FAIL_MSG);
    }
}
