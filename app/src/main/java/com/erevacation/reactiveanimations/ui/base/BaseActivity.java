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

package com.erevacation.reactiveanimations.ui.base;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewPropertyAnimator;

import com.erevacation.reactiveanimations.BR;
import com.erevacation.reactiveanimations.ReactiveAnimationApp;
import com.erevacation.reactiveanimations.injection.components.ActivityComponent;
import com.erevacation.reactiveanimations.injection.components.DaggerActivityComponent;
import com.erevacation.reactiveanimations.injection.modules.ActivityModule;
import com.erevacation.reactiveanimations.rxbus.RxEventBus;
import com.erevacation.reactiveanimations.rxjavaanimator.AnimatorObservableSource;
import com.erevacation.reactiveanimations.ui.base.navigator.Navigator;
import com.erevacation.reactiveanimations.ui.base.view.MvvmView;
import com.erevacation.reactiveanimations.ui.base.viewmodel.MvvmViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/* Base class for Activities when using a view model with data binding.
 * This class provides the binding and the view model to the subclass. The
 * view model is injected and the binding is created when the content view is set.
 * Each subclass therefore has to call the following code in onCreate():
 *    activityComponent().inject(this);
 *    setAndBindContentView(R.layout.my_activity_layout, savedInstanceState);
 *
 * After calling these methods, the binding and the view model is initialized.
 * saveInstanceState() and restoreInstanceState() methods of the view model
 * are automatically called in the appropriate lifecycle events when above calls
 * are made.
 *
 * Your subclass must implement the MvvmView implementation that you use in your
 * view model. */
public abstract class BaseActivity<B extends ViewDataBinding, V extends MvvmViewModel> extends
        AppCompatActivity {

    protected B binding;

    @Inject
    protected V viewModel;

    @Inject
    protected Handler handler;

    @Inject
    protected Navigator navigator;

    @Inject
    protected RxEventBus rxEventBus;

    @Inject
    protected FragmentManager fragmentManager;

    @Inject
    protected AnimatorObservableSource mAnimatorCompletableSource;

    @Inject
    protected Resources resources;

    private ActivityComponent mActivityComponent;

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    /* Use this method to set the content view on your Activity. This method also handles
     * creating the binding, setting the view model on the binding and attaching the view. */
    protected final void setAndBindContentView(@LayoutRes int layoutResId, @Nullable Bundle savedInstanceState) {

        if (viewModel == null) {
            throw new IllegalStateException(
                    "viewModel must not be null and should be injected via activityComponent().inject(this)");
        }

        binding = DataBindingUtil.setContentView(this, layoutResId);
        binding.setVariable(BR.vm, viewModel);
        //noinspection unchecked
        viewModel.attachView((MvvmView) this, savedInstanceState);
    }

    protected final ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(ReactiveAnimationApp.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return mActivityComponent;
    }

    @Override
    @CallSuper
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    @CallSuper
    public void finish() {
        super.finish();
    }

    @Override
    @CallSuper
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (viewModel != null) {
            viewModel.saveInstanceState(outState);
        }
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();

        if (viewModel != null) {
            viewModel.detachView();
        }

        binding = null;
        viewModel = null;
        mActivityComponent = null;
        handler.removeCallbacksAndMessages(null);
    }
}
