package com.kzw.constant;

import com.kzw.entity.UserEO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
public class SystemConstant {
    //图片地址
    public static final String PHOTO_PATH="C://photo/";// /photo
    //缩略图地址
    public static final String PHOTO_THUM_PATH="thumbnail/";
    //普通用户空间1G
    public static final long MAX_SIZE=1024;//*1048576
    //VIP用户空间10G
    public static final long VIP_MAX_SIZE=10240*1048576;

    public static Map<String,UserEO> USER_MAP =new HashMap<String,UserEO>();

}
