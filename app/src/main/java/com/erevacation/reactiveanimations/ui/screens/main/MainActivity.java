package com.erevacation.reactiveanimations.ui.screens.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Toast;

import com.erevacation.reactiveanimations.R;
import com.erevacation.reactiveanimations.databinding.ActivityMainBinding;
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
                4 * resources.getDimensionPixelOffset(R.dimen.activity_horizontal_margin);
    }

    @Override
    public void startAnimation(View view) {
        // clear observable
        compositeDisposable.clear();

        List<ViewPropertyAnimator> viewPropertyAnimators = new ArrayList<>();

        int viewCount = binding.animationObjects.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            viewPropertyAnimators.add(ViewAnimatorCreator
                    .animateTranslate(binding.animationObjects.getChildAt(i), animation_x,
                            i * 150));
        }

        compositeDisposable.add(mAnimatorCompletableSource
                .setAnimatorList(viewPropertyAnimators)
//                .doOnNext(viewPropertyAnimator -> {
//                    viewPropertyAnimator.alpha(0).setDuration(100).start();
//                })
                .doOnComplete(() -> {
                    Toast.makeText(this, "All animation Done", Toast.LENGTH_SHORT).show();
                })
                .subscribe());
    }

    @Override
    public void revertAnimation(View view) {
        // clear observable
        compositeDisposable.clear();

        List<ViewPropertyAnimator> viewPropertyAnimators = new ArrayList<>();

        int viewCount = binding.animationObjects.getChildCount();
        for (int i = viewCount - 1; i >= 0; i--) {
            viewPropertyAnimators.add(ViewAnimatorCreator
                    .animateTranslate(binding.animationObjects.getChildAt(i), 0, (viewCount - 1 - i) * 150));
        }


        compositeDisposable.add(mAnimatorCompletableSource
                .setAnimatorList(viewPropertyAnimators)
                .doOnComplete(() -> {
                    Toast.makeText(this, "All revert animation Done", Toast.LENGTH_SHORT).show();
                })
                .subscribe());
    }

    @Override
    public void stopAnimation(View view) {
        // clear observable
        compositeDisposable.clear();
        // clear all animation
        mAnimatorCompletableSource.clear();
        Toast.makeText(this, "All animation Stoped", Toast.LENGTH_SHORT).show();
    }
}
