package com.example.quangtruong.demo.constant;

public interface Constant {
    float END_SCALE = 0.7f;

    interface Url {
        String HOST = "http://192.168.88.185:8080";
    }

    interface UserTest {
        String USERNAME = "test@liferay.com";
        String PASSWORD = "123456";
    }

    interface ResultCode {
        int SIGN_UP = 1;
    }

    interface FormatString {
        String FORMAT_MSG = "{0} {1}";
    }

    interface ParcelKey {
        String USER_OBJECT = "USER_OBJECT";
    }

    interface DateTimeFormat {
        String YYYY_MM_DD = "yyyy-MM-dd";
    }

    interface SharePreference {
        String SHARE_PREF_NAME = "com.example.quangtruong.demo";
        String PREF_THEME = "PREF_THEME";
    }

    interface Theme {
        String THEME_DEFAULT = "THEME_DEFAULT";
        String THEME_PURPLE = "THEME_PURPLE";
    }
}
