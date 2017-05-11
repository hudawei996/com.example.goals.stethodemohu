package com.example.goals.stethodemohu;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.stetho.Stetho;

/**
 * Created by huyongqiang on 2017/5/10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 一定要记得初始化！！！
         */
        ActiveAndroid.initialize(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        //Stetho.initializeWithDefaults(this);
    }
}
