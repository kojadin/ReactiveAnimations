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

package com.erevacation.reactiveanimations.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.erevacation.reactiveanimations.BR;
import com.erevacation.reactiveanimations.ReactiveAnimationApp;
import com.erevacation.reactiveanimations.injection.components.DaggerViewHolderComponent;
import com.erevacation.reactiveanimations.injection.components.ViewHolderComponent;
import com.erevacation.reactiveanimations.injection.modules.ViewHolderModule;
import com.erevacation.reactiveanimations.injection.qualifier.ActivityContext;
import com.erevacation.reactiveanimations.ui.base.view.MvvmView;
import com.erevacation.reactiveanimations.ui.base.viewmodel.MvvmViewModel;

import javax.inject.Inject;

/* Base class for ViewHolders when using a view model with data binding.
 * This class provides the binding and the view model to the subclass. The
 * view model is injected and the binding is created when the content view is bound.
 * Each subclass therefore has to call the following code in the constructor:
 *    viewHolderComponent().inject(this);
 *    bindContentView(view);
 *
 * After calling these methods, the binding and the view model is initialized.
 * saveInstanceState() and restoreInstanceState() are not called/used for ViewHolder
 * view models.
 *
 * Your subclass must implement the MvvmView implementation that you use in your
 * view model. */
public abstract class BaseViewHolder<B extends ViewDataBinding, V extends MvvmViewModel> extends
        RecyclerView.ViewHolder {

    protected B binding;

    @Inject
    protected V viewModel;

    @ActivityContext
    @Inject
    protected Context activityContext;

    private ViewHolderComponent viewHolderComponent;

    private View itemView = null;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    protected final ViewHolderComponent viewHolderComponent() {
        if (viewHolderComponent == null) {
            viewHolderComponent = DaggerViewHolderComponent.builder()
                    .appComponent(ReactiveAnimationApp.getAppComponent())
                    .viewHolderModule(new ViewHolderModule(itemView))
                    .build();

            itemView = null;
        }

        return viewHolderComponent;
    }

    /* Use this method to create the binding for the ViewHolder. This method also handles
     * setting the view model on the binding and attaching the view. */
    protected final void bindContentView(@NonNull View view) {
        if (viewModel == null) {
            throw new IllegalStateException(
                    "viewModel must not be null and should be injected via viewHolderComponent()" +
                            ".inject(this)");
        }
        binding = DataBindingUtil.bind(view);
        binding.setVariable(BR.vm, viewModel);
        //noinspection unchecked
        viewModel.attachView((MvvmView) this, null);
    }

    public final V viewModel() {
        return viewModel;
    }

    public final void executePendingBindings() {
        if (binding != null) {
            binding.executePendingBindings();
        }
    }
}
