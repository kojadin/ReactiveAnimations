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

package com.erevacation.reactiveanimations.ui.screens.main;

import android.os.Bundle;
import android.view.View;

import com.erevacation.reactiveanimations.ui.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by kojadin on 12/8/16.
 */

public class MainViewModel extends BaseViewModel<MainMvvm.View> implements MainMvvm.ViewModel {


    @Inject
    public MainViewModel() {

    }

    @Override
    protected String getTag() {
        return MainViewModel.class.getSimpleName();
    }

    @Override
    public void startAnimation(View view) {
        getView().startAnimation(view);
    }

    @Override
    public void revertAnimation(View view) {
        getView().revertAnimation(view);
    }

    @Override
    public void stopAnimation(View view) {
        getView().stopAnimation(view);
    }

    @Override
    public void attachView(MainMvvm.View view, Bundle savedInstanceState) {
        super.attachView(view, savedInstanceState);
    }
}
