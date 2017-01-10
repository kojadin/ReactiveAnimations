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

import com.erevacation.reactiveanimations.ui.base.view.MvvmView;
import com.erevacation.reactiveanimations.ui.base.viewmodel.MvvmViewModel;

/**
 * Created by Master Milivoje Kojadinović and
 * Padawan David Lleixà on 01/11/16.
 */

public interface MainMvvm {
    interface View extends MvvmView {
        void startAnimation(android.view.View view);

        void revertAnimation(android.view.View view);

        void stopAnimation(android.view.View view);
    }

    interface ViewModel extends MvvmViewModel<View> {
        void startAnimation(android.view.View view);

        void revertAnimation(android.view.View view);

        void stopAnimation(android.view.View view);
    }
}
