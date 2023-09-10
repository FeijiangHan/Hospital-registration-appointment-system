package com.asiait.yygh.until;

import android.app.Application;


public class ConfigApplication extends Application {
    private static ConfigApplication app;//实例化
    public static user u;

    public static user getU() {
        return u;
    }

    public static void setU(user u) {
        ConfigApplication.u = u;
    }
    public static ConfigApplication getApp() {
        if (app == null)
            app = new ConfigApplication();
        return app;
    }

    public static void setApp(ConfigApplication app) {
        ConfigApplication.app = app;
    }


}
