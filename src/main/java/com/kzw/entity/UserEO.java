package com.kzw.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author panaidong
 * @version V1.0
 * @Title: UserEO
 * @Description: 用户信息
 * @date
 */

@Entity
@Table(name="user")//表名称
public class UserEO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String userName;
    private String userCode;//openId
    private String vipFlag;//是否VIP 1是0否
    private String uniqueFlag;//微信用户唯一标志(本系统内的)

    public String getUniqueFlag() {
        return uniqueFlag;
    }

    public void setUniqueFlag(String uniqueFlag) {
        this.uniqueFlag = uniqueFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }


    public String getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(String vipFlag) {
        this.vipFlag = vipFlag;
    }

    public UserEO(String userCode, String userName){
        this.userCode=userCode;
        this.userName=userName;

        this.vipFlag="0";//0非VIP;1VIP
    }
    public UserEO(){}

}
