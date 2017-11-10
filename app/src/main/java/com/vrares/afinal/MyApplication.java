package com.vrares.afinal;

import android.app.Application;

import toothpick.Scope;
import toothpick.Toothpick;

public class MyApplication extends Application {
    private static MyApplication instance;
    private Scope scope;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        scope  = Toothpick.openScope(this);
        Toothpick.inject(this, scope);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
