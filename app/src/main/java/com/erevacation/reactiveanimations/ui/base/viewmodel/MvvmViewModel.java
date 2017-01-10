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

package com.erevacation.reactiveanimations.ui.base.viewmodel;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.erevacation.reactiveanimations.ui.base.view.MvvmView;

public interface MvvmViewModel<V extends MvvmView> extends Observable {

    void attachView(V view, Bundle savedInstanceState);

    void detachView();

    void saveInstanceState(@NonNull Bundle outState);

    void restoreInstanceState(@Nullable Bundle savedInstanceState);

    void addObservables();
}
