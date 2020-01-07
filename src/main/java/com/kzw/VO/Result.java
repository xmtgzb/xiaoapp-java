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
    private static Result res;
    private static  String success="200";
    private static  String faile="500";
    private String code ;//200代表成功，其他代表失败
    private String msg ;
    private static Result getResult(String code,String msg){
        if (res==null){
            Result r = new Result();
            r.put("code",code);
            r.put("msg",msg);
            res =r;
        }else{
            res.put("code",code);
            res.put("msg",msg);
        }
        return res;
    }
    public static Result ok(){
        return getResult(success,"");
    }
    public static Result ok(String msg){
        return  getResult(success,msg);
    }
    public static Result error(){
        return getResult(faile,"系统错误，请联系管理员！！！");
    }
    public static Result error(String msg){
        return getResult(faile,msg);
    }
    public Result put(String key,Object value){
        super.put(key,value);
        return this;
    }
    public  boolean isError(){
        if (success.equals(this.res.get("code"))){
            return false;
        }
        return true;
    }

}
