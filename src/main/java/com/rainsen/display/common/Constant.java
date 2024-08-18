package com.rainsen.display.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

    public static final String USER_SALT = "$*(^%@ghasd09863)(*23{{]khsda106";

    public static final String JWT_KEY_NAME = "jwt_key";
    public static final String JWT_TOKEN_NAME = "token"; // parameter name from client
    public static final String JWT_LOAD_USER_ID = "user_id";
    public static final String JWT_LOAD_USER_NAME = "user_name";
    public static final String JWT_LOAD_USER_ROLE = "user_role";
    public static final Long JWT_EXPIRE_TIME = 1000 * 60 * 60 * 24L; // millisecond

    public static String APP_URL;
    public static String FILE_UPLOAD_DIR;
    public static String FILE_ACCESS_PREFIX;

    @Value("${spring.application.url}")
    public void setAppUrl(String appUrl) {
        APP_URL = appUrl;
        FILE_ACCESS_PREFIX = appUrl + "/file/";
    }

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}
