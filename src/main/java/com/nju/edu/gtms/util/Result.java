package com.nju.edu.gtms.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object result;

    public Result(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public static Result success(Object result){
        return new Result( "00000","Success",result);
    }
    public static Result success(){
        return new Result( "00000","Success");
    }

    public static Result fail(){
        return new Result( "11111","请登录");
    }
}
