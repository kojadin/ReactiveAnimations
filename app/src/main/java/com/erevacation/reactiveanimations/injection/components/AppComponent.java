/*
 * Copyright 2016 Bonagora, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.erevacation.reactiveanimations.injection.components;

import android.content.Context;
import android.content.res.Resources;

import com.erevacation.reactiveanimations.injection.modules.AppModule;
import com.erevacation.reactiveanimations.injection.qualifier.AppContext;
import com.erevacation.reactiveanimations.injection.scopes.PerApplication;
import com.erevacation.reactiveanimations.rxbus.RxEventBus;

import dagger.Component;

@PerApplication
@Component(modules = { AppModule.class })
public interface AppComponent {

    @AppContext
    Context context();

    Resources resources();

    RxEventBus rxEventBus();
}
