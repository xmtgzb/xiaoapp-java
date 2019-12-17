package com.kzw.VO;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
public class BaseQueryVO {
private Integer page;
private Integer pageSize;

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  private String xiangCe;
private String userCode;

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  private  String isSee;

  public String getIsSee() {
    return isSee;
  }

  public void setIsSee(String isSee) {
    this.isSee = isSee;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public String getXiangCe() {
    return xiangCe;
  }

  public void setXiangCe(String xiangCe) {
    this.xiangCe = xiangCe;
  }
}
