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
  public static String  appid="wxd5cd5d71471c353c";
  public static String  SECRET="c74d7d272814f69f1467e3237f10348a";
  public  String  openId;

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }
}
