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
