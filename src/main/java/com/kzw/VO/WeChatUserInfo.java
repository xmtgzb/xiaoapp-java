package com.kzw.VO;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
public class WeChatUserInfo {
  public static String  loginUrl="https://api.weixin.qq.com/sns/jscode2session?";
  public static String  appid="wxaf3f1b68a9f8d7b0";
  public static String  SECRET="2e4348f8e38d9f41f78a4dc40c729c18";
  public  String  openId;

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }
}
