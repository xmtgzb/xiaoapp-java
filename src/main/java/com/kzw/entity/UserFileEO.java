package com.kzw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
@Entity
@Table(name="user_file")//表名称
public class UserFileEO {
    private String userCode;
    private String fileName;
    @Column(name="file_Desc")
    private String fileDesc;
    private String isSee;
    private String sltPath;
    private String realPath;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String xiangCe;
    private Date insertTime;

    public Date getInsertTime() {
        return insertTime;
    }

    public UserFileEO() {

        this.isSee = "0";//不对外展示
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getIsSee() {
        return isSee;
    }

    public void setIsSee(String isSee) {
        this.isSee = isSee;
    }

    public String getSltPath() {
        return sltPath;
    }

    public void setSltPath(String slt_path) {
        this.sltPath = slt_path;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getXiangCe() {
        return xiangCe;
    }

    public void setXiangCe(String xiangCe) {
        this.xiangCe = xiangCe;
    }

    public Date getInserTime() {
        return insertTime;
    }

    public void setInsertTime(Date insert_time) {
        this.insertTime = insert_time;
    }
}
