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

package com.erevacation.reactiveanimations.injection.modules;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.erevacation.reactiveanimations.injection.qualifier.ActivityContext;
import com.erevacation.reactiveanimations.injection.scopes.PerViewHolder;
import com.erevacation.reactiveanimations.ui.base.navigator.ActivityNavigator;
import com.erevacation.reactiveanimations.ui.base.navigator.Navigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewHolderModule {

    private final AppCompatActivity activity;

    public ViewHolderModule(View itemView) {
        activity = (AppCompatActivity) itemView.getContext();
    }

    @Provides
    @PerViewHolder
    @ActivityContext
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @PerViewHolder
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @PerViewHolder
    Navigator provideNavigator() {
        return new ActivityNavigator(activity);
    }

}
