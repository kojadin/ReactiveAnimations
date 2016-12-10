package com.erevacation.reactiveanimations.ui.base.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;

import com.erevacation.reactiveanimations.injection.qualifier.AppContext;
import com.erevacation.reactiveanimations.rxbus.RxEventBus;
import com.erevacation.reactiveanimations.ui.base.view.MvvmView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Master Milivoje Kojadinović and
 * Padawan David Lleixà on 29/11/16.
 */

public abstract class BaseDialogViewModel<V extends MvvmView> extends BaseObservable implements MvvmViewModel<V> {

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @AppContext
    @Inject
    protected Context appContext;

    @Inject
    protected ContextThemeWrapper contextThemeWrapper;

    @Inject
    protected Resources resources;

    @Inject
    protected RxEventBus rxEventBus;

    private V mView;

    public V getView() {
        return mView;
    }

    @Override
    @CallSuper
    public void attachView(@NonNull V view, @Nullable Bundle savedInstanceState) {
        mView = view;

        /** Always trigger {@link MvvmViewModel#restoreInstanceState(Bundle)} in order to take advantage of
         * our way to restore states through {@link android.content.SharedPreferences} **/
        restoreInstanceState(savedInstanceState);

        addObservables();
    }

    @Override
    @CallSuper
    public void detachView() {
        mView = null;
        compositeDisposable.clear();
    }

    @Override
    public void saveInstanceState(@NonNull Bundle outState) {
    }

    @Override
    @CallSuper
    public void restoreInstanceState(@Nullable Bundle savedInstanceState) {
    }

    @Override
    @CallSuper
    public void addObservables() {
    }

    protected abstract String getTag();
}
