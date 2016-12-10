package com.erevacation.reactiveanimations;

import android.app.Application;

import com.erevacation.reactiveanimations.injection.components.AppComponent;
import com.erevacation.reactiveanimations.injection.components.DaggerAppComponent;
import com.erevacation.reactiveanimations.injection.modules.AppModule;

import timber.log.Timber;

/**
 * Created by kojadin on 12/7/16.
 */

public class ReactiveAnimationApp extends Application {

    private static ReactiveAnimationApp sInstance = null;

    private static AppComponent sAppComponent = null;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
