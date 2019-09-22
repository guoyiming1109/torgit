package com.example.dell.a;

import android.app.Application;

/**
 * Created by DELL on 2019/9/20.
 */

public class App extends Application {
    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
