/*
 *
 *  * Copyright 2017 Kojadin
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.erevacation.reactiveanimations.injection.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.erevacation.reactiveanimations.injection.qualifier.AppContext;
import com.erevacation.reactiveanimations.injection.scopes.PerApplication;
import com.erevacation.reactiveanimations.rxbus.RxEventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private static final long SCHEMA_VERSION_REALM = 1;

    private static final String MAIN_REALM = "main_realm.realm";

    private final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @PerApplication
    public static RxEventBus provideRxEventBus() {
        return new RxEventBus();
    }

    @Provides
    @PerApplication
    @AppContext
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    public Resources provideResources() {
        return mApp.getResources();
    }
}
