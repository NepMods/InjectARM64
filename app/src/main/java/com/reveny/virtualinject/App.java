package com.reveny.virtualinject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.vcore.BlackBoxCore;
import com.vcore.app.configuration.ClientConfiguration;

public class App extends Application {
    private static App instance = null;
    private SharedPreferences pref;
    public static SharedPreferences getPreferences() {
        return instance.pref;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            BlackBoxCore.get().doAttachBaseContext(base, new ClientConfiguration() {
                @Override
                public String getHostPackageName() {
                    return base.getPackageName();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        BlackBoxCore.get().doCreate();
    }
}