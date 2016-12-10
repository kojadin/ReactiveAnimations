package com.erevacation.reactiveanimations.ui.screens.main;

import android.view.View;

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
