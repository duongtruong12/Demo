package com.example.quangtruong.demo.app;

import android.support.multidex.MultiDexApplication;

import com.example.quangtruong.demo.constant.Constant;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;

public class AppController extends MultiDexApplication {
    public static final String TAG = AppController.class.getSimpleName();

    private static AppController mInstance;
    private static Session session;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static Session getSession() {
        if (session == null)
            session = new SessionImpl(Constant.Url.HOST, new BasicAuthentication(Constant.UserTest.USERNAME, Constant.UserTest.PASSWORD));
        return session;
    }

    public static void setSession(String username, String password) {
        AppController.session = new SessionImpl(Constant.Url.HOST, new BasicAuthentication(username, password));
    }
}