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
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

public abstract class BaseNavigator implements Navigator {

    private FragmentTransactionAnimations mFragmentAnimations = new FragmentTransactionAnimations();

    private FragmentTransactionAnimations mFragmentBackStackAnimations =
            new FragmentTransactionAnimations();

    private ActivityTransitionAnimations mActivityAnimations = new ActivityTransitionAnimations();

    abstract FragmentActivity getActivity();

    abstract FragmentManager getChildFragmentManager();

    @Override
    public final void finishActivity() {
        getActivity().finish();
        mActivityAnimations.apply(getActivity());
    }

    @Override
    public final void startActivity(@NonNull Intent intent) {
        getActivity().startActivity(intent);
        mActivityAnimations.apply(getActivity());
    }

    @Override
    public final void startActivity(@NonNull String action) {
        getActivity().startActivity(new Intent(action));
        mActivityAnimations.apply(getActivity());
    }

    @Override
    public final void startActivity(@NonNull String action, @NonNull Uri uri) {
        getActivity().startActivity(new Intent(action, uri));
        mActivityAnimations.apply(getActivity());
    }

    @Override
    public final void startActivity(@NonNull Class<? extends Activity> activityClass) {
        startActivity(activityClass, null);
    }

    @Override
    public final void startActivity(@NonNull Class<? extends Activity> activityClass, Bundle args) {
        Activity activity = getActivity();
        Intent intent = new Intent(activity, activityClass);
        if (args != null) {
            intent.putExtra(EXTRA_ARGS, args);
        }
        activity.startActivity(intent);
        mActivityAnimations.apply(getActivity());
    }

    @Override
    public final void startActivity(@NonNull Class<? extends Activity> activityClass,
            Parcelable args) {
        Activity activity = getActivity();
        Intent intent = new Intent(activity, activityClass);
        if (args != null) {
            intent.putExtra(EXTRA_ARGS, args);
        }
        activity.startActivity(intent);
        mActivityAnimations.apply(getActivity());
    }

    @Override
    public final void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment,
            Bundle args) {
        replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment,
                null, args, false,
                null);
    }

    @Override
    public final void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag,
            Bundle args) {
        replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment,
                fragmentTag, args,
                false, null);
    }

    @Override
    public final void replaceFragmentAndAddToBackStack(@IdRes int containerId,
            @NonNull Fragment fragment, Bundle args,
            String backStackTag) {
        replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment,
                null, args, true,
                backStackTag);
    }

    @Override
    public final void replaceFragmentAndAddToBackStack(@IdRes int containerId,
            @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args, String backStackTag) {
        replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment,
                fragmentTag, args,
                true, backStackTag);
    }

    @Override
    public final void replaceFragmentAndAddToBackStack(@IdRes int containerId,
            @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args) {
        replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment,
                fragmentTag, args,
                true, null);
    }

    @Override
    public final void replaceFragmentAndAddToBackStack(@IdRes int containerId,
            @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args, View sharedView, String transitionName) {
        replaceFragmentInternalWithSharedElement(getActivity().getSupportFragmentManager(),
                containerId, fragment, fragmentTag, args,
                true, null, sharedView, transitionName);
    }

    @Override
    public final void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment,
            Bundle args) {
        replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, null, args, false,
                null);
    }

    @Override
    public final void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args) {
        replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, fragmentTag, args,
                false, null);
    }

    @Override
    public final void replaceChildFragmentAndAddToBackStack(@IdRes int containerId,
            @NonNull Fragment fragment,
            Bundle args, String backStackTag) {
        replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, null, args, true,
                backStackTag);
    }

    @Override
    public final void replaceChildFragmentAndAddToBackStack(@IdRes int containerId,
            @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args, String backStackTag) {
        replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, fragmentTag, args,
                true,
                backStackTag);
    }

    @Override
    public final void replaceChildFragmentAndAddToBackStack(@IdRes int containerId,
            @NonNull Fragment fragment,
            @NonNull String fragmentTag, Bundle args) {
        replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, fragmentTag, args,
                true, null);
    }

    @Override
    public final void setFragmentCustomAnimations(@AnimRes int enter, @AnimRes int exit) {
        mFragmentAnimations.setAnimations(enter, exit, 0, 0);
    }

    @Override
    public final void setFragmentCustomAnimations(@AnimRes int enter, @AnimRes int exit,
            @AnimRes int popEnter,
            @AnimRes int popExit) {
        mFragmentAnimations.setAnimations(enter, exit, popEnter, popExit);
    }

    @Override
    public final void setFragmentBackStackCustomAnimations(@AnimRes int enter, @AnimRes int exit) {
        mFragmentBackStackAnimations.setAnimations(enter, exit, 0, 0);
    }

    @Override
    public final void setFragmentBackStackCustomAnimations(@AnimRes int enter, @AnimRes int exit,
            @AnimRes int popEnter,
            @AnimRes int popExit) {
        mFragmentBackStackAnimations.setAnimations(enter, exit, popEnter, popExit);
    }

    @Override
    public void setActivityCustomAnimations(@AnimRes int enter, @AnimRes int exit) {
        mActivityAnimations.setAnimations(enter, exit);
    }

    private void replaceFragmentInternal(FragmentManager fm, @IdRes int containerId,
            Fragment fragment,
            String fragmentTag, Bundle args, boolean addToBackStack, String backStackTag) {

        if (args != null) {
            fragment.setArguments(args);
        }

        if (addToBackStack) {
            Fragment replacedFragment = fm.findFragmentById(containerId);
            if (replacedFragment != null) {
                fm.saveFragmentInstanceState(replacedFragment);
            }

            mFragmentBackStackAnimations.apply(fm.beginTransaction())
                    .replace(containerId, fragment, fragmentTag)
                    .addToBackStack(backStackTag)
                    .commit();
            fm.executePendingTransactions();
        } else {
            mFragmentAnimations.apply(fm.beginTransaction())
                    .replace(containerId, fragment, fragmentTag)
                    .commitNow();
        }
    }

    private void replaceFragmentInternalWithSharedElement(FragmentManager fm,
            @IdRes int containerId, Fragment fragment,
            String fragmentTag, Bundle args, boolean addToBackStack, String backStackTag,
            View sharedElement, String transitionName) {

        if (args != null) {
            fragment.setArguments(args);
        }

        if (addToBackStack) {
            Fragment replacedFragment = fm.findFragmentById(containerId);
            if (replacedFragment != null) {
                fm.saveFragmentInstanceState(replacedFragment);
            }

            mFragmentBackStackAnimations.apply(fm.beginTransaction())
                    .addSharedElement(sharedElement, transitionName)
                    .replace(containerId, fragment, fragmentTag)
                    .addToBackStack(backStackTag)
                    .commit();
            fm.executePendingTransactions();
        } else {
            mFragmentAnimations.apply(fm.beginTransaction())
                    .replace(containerId, fragment, fragmentTag)
                    .commitNow();
        }
    }
}
