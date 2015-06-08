package com.android.hrtkzt.pog.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by hirotakazuto on 15/06/07.
 */
public class POGApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this).enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
