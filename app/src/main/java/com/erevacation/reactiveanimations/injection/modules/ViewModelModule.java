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

import com.erevacation.reactiveanimations.ui.screens.main.MainMvvm;
import com.erevacation.reactiveanimations.ui.screens.main.MainViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    // Activities

    @Binds
    abstract MainMvvm.ViewModel bindMainViewModel(MainViewModel viewModel);

    // Fragments
//    @Binds
//    abstract LoginFormMvvm.ViewModel bindLoginFormViewModel(LoginFormViewModel loginFormViewModel);

    // View Holders
//    @Binds
//    abstract ProductMvvm.ViewModel bindProductViewModel(ProductViewModel productViewModel);
}
