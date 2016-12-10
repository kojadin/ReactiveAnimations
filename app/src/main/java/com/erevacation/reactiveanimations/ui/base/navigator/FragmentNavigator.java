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

package com.erevacation.reactiveanimations.ui.base.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class FragmentNavigator extends BaseNavigator {

    private final Fragment mFragment;

    public FragmentNavigator(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    final FragmentActivity getActivity() {
        return mFragment.getActivity();
    }

    @Override
    final FragmentManager getChildFragmentManager() {
        return mFragment.getChildFragmentManager();
    }


}
