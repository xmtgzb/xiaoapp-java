package com.kzw.VO;

import java.util.HashMap;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
public class Result extends HashMap<String,Object>{
    private String code ;//200代表成功，其他代表失败
    private String msg ;
    private Result(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public static Result ok(){
        return new Result("200","");
    }
    public static Result ok(String msg){
        return new Result("200",msg);
    }
    public static Result error(){
        return new Result("500","系统错误，请联系管理员！！！");
    }
    public static Result error(String msg){
        return new Result("500",msg);
    }
    public Result put(String key,Object value){
        super.put(key,value);
        return this;
    }
    public  boolean isError(){
        if (this.code.equals("200")){
            return false;
        }
        return true;
    }

}
