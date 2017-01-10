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

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Toast;

import com.erevacation.reactiveanimations.R;
import com.erevacation.reactiveanimations.databinding.ActivityMainBinding;
import com.erevacation.reactiveanimations.rxjavaanimator.ObservableAnimator;
import com.erevacation.reactiveanimations.rxjavaanimator.ViewAnimatorCreator;
import com.erevacation.reactiveanimations.ui.base.BaseActivity;
import com.erevacation.reactiveanimations.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements
        MainMvvm.View {

    private int animation_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setAndBindContentView(R.layout.activity_main, savedInstanceState);

        animation_x = ViewUtils.getDisplayDimensions(this).first -
                5 * resources.getDimensionPixelOffset(R.dimen.activity_horizontal_margin);
    }

    @Override
    public void startAnimation(View view) {
        view.setEnabled(false);
        // clear observable
        compositeDisposable.clear();

        List<ViewPropertyAnimator> viewPropertyAnimators = new ArrayList<>();

        int viewCount = binding.animationObjects.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            viewPropertyAnimators
                    .add(binding.animationObjects.getChildAt(i).animate().rotation(90)
                            .setDuration(200));
            viewPropertyAnimators.add(ViewAnimatorCreator
                    .animateTranslate(binding.animationObjects.getChildAt(i), animation_x,
                            i * 150));
        }

        compositeDisposable.add(ObservableAnimator.fromPropertyAnimator(viewPropertyAnimators)
                .doOnNext(viewPropertyAnimator -> {
                    viewPropertyAnimator.rotation(180).setDuration(200)
                            .start();
                })
                .doOnComplete(() -> {
                    view.setEnabled(true);
                    Toast.makeText(this, "All animation Done", Toast.LENGTH_SHORT).show();
                })
                .subscribe());
    }

    @Override
    public void revertAnimation(View view) {

        view.setEnabled(false);
        // clear observable
        compositeDisposable.clear();

        List<ViewPropertyAnimator> viewPropertyAnimators = new ArrayList<>();

        int viewCount = binding.animationObjects.getChildCount();
        for (int i = viewCount - 1; i >= 0; i--) {
            viewPropertyAnimators
                    .add(binding.animationObjects.getChildAt(i).animate().rotation(90)
                            .setDuration(200));
            viewPropertyAnimators.add(ViewAnimatorCreator
                    .animateTranslate(binding.animationObjects.getChildAt(i), 0,
                            (viewCount - 1 - i) * 150));
        }

        compositeDisposable.add(ObservableAnimator.fromPropertyAnimator(viewPropertyAnimators)
                .doOnNext(viewPropertyAnimator -> {
                    viewPropertyAnimator.rotation(0).setDuration
                            (200).start();
                })
                .doOnComplete(() -> {
                    view.setEnabled(true);
                    Toast.makeText(this, "All reverse animation Done", Toast.LENGTH_SHORT)
                            .show();
                })
                .subscribe());
    }

    @Override
    public void stopAnimation(View view) {
        // clear observable
        compositeDisposable.clear();
        Toast.makeText(this, "All animation Stoped", Toast.LENGTH_SHORT).show();
    }
}
