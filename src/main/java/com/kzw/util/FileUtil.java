package com.kzw.util;

import com.kzw.constant.SystemConstant;

import java.io.File;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
public class FileUtil {
    public static long getFileSize(String patch) {
        File filePath =new File(patch) ;
        return filePath.length();
    }
}
