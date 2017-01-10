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

package com.erevacation.reactiveanimations.injection.components;

import com.erevacation.reactiveanimations.injection.modules.ActivityModule;
import com.erevacation.reactiveanimations.injection.modules.ViewModelModule;
import com.erevacation.reactiveanimations.injection.scopes.PerActivity;
import com.erevacation.reactiveanimations.ui.screens.main.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,
        modules = { ActivityModule.class, ViewModelModule.class })
public interface ActivityComponent {

    void inject(MainActivity activity);
}
