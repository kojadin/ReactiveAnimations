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

package com.erevacation.reactiveanimations.rxjavaanimator;

import android.animation.TimeInterpolator;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewPropertyAnimator;

import timber.log.Timber;

/**
 * Created by Master Milivoje Kojadinović and
 * Padawan David Lleixà on 01/12/16.
 */

public class ViewAnimatorCreator {

    public final static int DEFAULT_ANIMATION_DELAY = 300;
    public final static int DEFAULT_ANIMATION_DURATION = 1000;

    /*
    ============================================

    SCALE UP

    ============================================
    */
    public static ViewPropertyAnimator animateScaleUp(View view) {
        return animateScaleUp(view, DEFAULT_ANIMATION_DURATION, DEFAULT_ANIMATION_DELAY, null);
    }

    public static ViewPropertyAnimator animateScaleUp(View view, TimeInterpolator interpolator) {
        return animateScaleUp(view, DEFAULT_ANIMATION_DURATION, DEFAULT_ANIMATION_DELAY,
                interpolator);
    }

    public static ViewPropertyAnimator animateScaleUp(View view, int delay) {
        return animateScaleUp(view, DEFAULT_ANIMATION_DURATION, delay, null);
    }

    public static ViewPropertyAnimator animateScaleUp(View view, int delay,
            TimeInterpolator interpolator) {
        return animateScaleUp(view, DEFAULT_ANIMATION_DURATION, delay, interpolator);
    }

    private static ViewPropertyAnimator animateScaleUp(View view, int duration, int delay,
            TimeInterpolator interpolator) {
        view.setScaleX(0);
        view.setScaleY(0);
        ViewPropertyAnimator propertyAnimator = view.animate()
                .scaleX(1)
                .scaleY(1)
                .setStartDelay(delay)
                .setDuration(duration);

        if (interpolator != null) {
            propertyAnimator.setInterpolator(interpolator);
        } else {
            propertyAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        }

        return propertyAnimator;
    }

    /*
    ============================================

    SCALE DOWN

    ============================================
    */
    public static ViewPropertyAnimator animateScaleDown(View view) {
        return animateScaleDown(view, DEFAULT_ANIMATION_DURATION, 0, null);
    }

    public static ViewPropertyAnimator animateScaleDown(View view, int delay) {
        return animateScaleDown(view, DEFAULT_ANIMATION_DURATION, delay, null);
    }

    public static ViewPropertyAnimator animateScaleDown(View view, TimeInterpolator interpolator) {
        return animateScaleDown(view, DEFAULT_ANIMATION_DURATION, 0, interpolator);
    }

    public static ViewPropertyAnimator animateScaleDown(View view, int delay,
            TimeInterpolator interpolator) {
        return animateScaleDown(view, DEFAULT_ANIMATION_DURATION, delay, interpolator);
    }

    private static ViewPropertyAnimator animateScaleDown(View view, int duration, int delay,
            TimeInterpolator interpolator) {
        view.setScaleX(1);
        view.setScaleY(1);
        ViewPropertyAnimator propertyAnimator = view.animate()
                .scaleX(0)
                .scaleY(0)
                .setStartDelay(delay)
                .setDuration(duration);
        if (interpolator != null) {
            propertyAnimator.setInterpolator(interpolator);
        } else {
            propertyAnimator.setInterpolator(new FastOutLinearInInterpolator());
        }

        return propertyAnimator;
    }


    /*
    ============================================

    FADE IN

    ============================================
    */
    public static ViewPropertyAnimator animateFadeIn(View view) {
        return animateFadeIn(view, DEFAULT_ANIMATION_DURATION, DEFAULT_ANIMATION_DELAY, null);
    }

    public static ViewPropertyAnimator animateFadeIn(View view, int delay) {
        return animateFadeIn(view, DEFAULT_ANIMATION_DURATION, delay, null);
    }

    public static ViewPropertyAnimator animateFadeIn(View view, TimeInterpolator interpolator) {
        return animateFadeIn(view, DEFAULT_ANIMATION_DURATION, DEFAULT_ANIMATION_DELAY,
                interpolator);
    }

    public static ViewPropertyAnimator animateFadeIn(View view, int delay,
            TimeInterpolator interpolator) {
        return animateScaleDown(view, DEFAULT_ANIMATION_DURATION, delay, interpolator);
    }

    private static ViewPropertyAnimator animateFadeIn(View view, int duration, int delay,
            TimeInterpolator interpolator) {
        view.setAlpha(0);
        ViewPropertyAnimator propertyAnimator = view.animate()
                .alpha(1)
                .setStartDelay(delay)
                .setDuration(duration);
        if (interpolator != null) {
            propertyAnimator.setInterpolator(interpolator);
        } else {
            propertyAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        }

        return propertyAnimator;
    }

    /*
    ============================================

    FADE OUT

    ============================================
    */
    public static ViewPropertyAnimator animateFadeOut(View view) {
        return animateFadeOut(view, DEFAULT_ANIMATION_DURATION, 0, null);
    }

    public static ViewPropertyAnimator animateFadeOut(View view, int delay) {
        return animateFadeOut(view, DEFAULT_ANIMATION_DURATION, delay, null);
    }

    public static ViewPropertyAnimator animateFadeOut(View view, TimeInterpolator interpolator) {
        return animateFadeOut(view, DEFAULT_ANIMATION_DURATION, 0, interpolator);
    }

    public static ViewPropertyAnimator animateFadeOut(View view, int delay,
            TimeInterpolator interpolator) {
        return animateFadeOut(view, DEFAULT_ANIMATION_DURATION, delay, interpolator);
    }

    private static ViewPropertyAnimator animateFadeOut(View view, int duration, int delay,
            TimeInterpolator interpolator) {
        view.setAlpha(1);
        ViewPropertyAnimator propertyAnimator = view.animate()
                .alpha(0)
                .setStartDelay(delay)
                .setDuration(duration);
        if (interpolator != null) {
            propertyAnimator.setInterpolator(interpolator);
        } else {
            propertyAnimator.setInterpolator(new FastOutLinearInInterpolator());
        }

        return propertyAnimator;
    }

    /*
    ============================================

    translate right

    ============================================
    */
    public static ViewPropertyAnimator animateTranslate(View view, int dx) {
        return animateTranslate(view, DEFAULT_ANIMATION_DURATION, 0, dx, null);
    }

    public static ViewPropertyAnimator animateTranslate(View view, int dx, int delay) {
        return animateTranslate(view, DEFAULT_ANIMATION_DURATION, delay, dx, null);
    }

    private static ViewPropertyAnimator animateTranslate(View view, int duration, int delay, int dx,
            TimeInterpolator interpolator) {
        Timber.d("Translate for dx: " + dx);
        view.setAlpha(1);
        ViewPropertyAnimator propertyAnimator = view.animate()
                .translationX(dx)
                .setStartDelay(delay)
                .setDuration(duration);
        if (interpolator != null) {
            propertyAnimator.setInterpolator(interpolator);
        } else {
            propertyAnimator.setInterpolator(new FastOutLinearInInterpolator());
        }

        return propertyAnimator;
    }
}
