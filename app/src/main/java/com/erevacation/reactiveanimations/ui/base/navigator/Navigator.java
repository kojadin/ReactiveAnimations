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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

public interface Navigator {
    String EXTRA_ARGS = "_args";

    void finishActivity();

    void startActivity(@NonNull Intent intent);

    void startActivity(@NonNull String action);

    void startActivity(@NonNull String action, @NonNull Uri uri);

    void startActivity(@NonNull Class<? extends Activity> activityClass);

    void startActivity(@NonNull Class<? extends Activity> activityClass, Bundle args);

    void startActivity(@NonNull Class<? extends Activity> activityClass, Parcelable args);

    void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment, Bundle args);

    void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args);

    void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
            Bundle args,
            String backStackTag);

    void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args, String backStackTag);

    void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args);

    void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args, View sharedView, String transitionName);

    void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment, Bundle args);

    void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag,
            Bundle args);

    void replaceChildFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
            Bundle args,
            String backStackTag);

    void replaceChildFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args, String backStackTag);

    void replaceChildFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args);

    /**
     * Sets custom animations for fragment transactions when replacing fragments.
     */
    void setFragmentCustomAnimations(@AnimRes int enter, @AnimRes int exit);

    /**
     * Sets custom animations for fragment transactions when replacing fragments.
     */
    void setFragmentCustomAnimations(@AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter,
            @AnimRes int popExit);

    /**
     * Sets custom animations for fragment transactions which are added
     * to the back stack.
     */
    void setFragmentBackStackCustomAnimations(@AnimRes int enter, @AnimRes int exit);

    /**
     * Sets custom animations for fragment transactions which are added
     * to the back stack.
     */
    void setFragmentBackStackCustomAnimations(@AnimRes int enter, @AnimRes int exit,
            @AnimRes int popEnter,
            @AnimRes int popExit);

    /**
     * Sets custom animations for activity transitions
     */
    void setActivityCustomAnimations(@AnimRes int enter, @AnimRes int exit);
}
