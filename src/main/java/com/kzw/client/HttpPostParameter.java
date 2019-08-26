package com.kzw.client;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
import java.io.File;

public class HttpPostParameter {

    String name;
    String value;
    private File file = null;

    private static final long serialVersionUID = -8708108746980739212L;

    public HttpPostParameter(String name, String value) {

        this.name = name;
        this.value = value;

    }
}