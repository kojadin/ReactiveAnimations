package com.erevacation.reactiveanimations.rxjavaanimator;

import android.view.ViewPropertyAnimator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.operators.observable.ObservableEmpty;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by kojadin on 12/10/16.
 */

public class ObservableAnimator extends Observable<ViewPropertyAnimator> {

    ViewPropertyAnimator[] mPropertyAnimators;

    public ObservableAnimator(ViewPropertyAnimator... viewPropertyAnimatorList) {
        this.mPropertyAnimators = viewPropertyAnimatorList;
    }

    @Override
    protected void subscribeActual(Observer<? super ViewPropertyAnimator> o) {
        AnimatorDisposable parent = new AnimatorDisposable(o, mPropertyAnimators);
        o.onSubscribe(parent);
        parent.run();
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Observable<ViewPropertyAnimator> fromPropertyAnimator(
            List<ViewPropertyAnimator> viewPropertyAnimatorList) {
        if (viewPropertyAnimatorList == null) {
            throw new IllegalArgumentException("viewPropertyAnimatorList required but it was null");
        }
        if (viewPropertyAnimatorList.size() == 0) {
            return empty();
        }
        return RxJavaPlugins.onAssembly(new ObservableAnimator(viewPropertyAnimatorList.toArray(new ViewPropertyAnimator[viewPropertyAnimatorList.size()])));
    }

    static final class AnimatorDisposable
            extends BasicIntQueueDisposable<ViewPropertyAnimator> {

        final Observer<? super ViewPropertyAnimator> actual;

        final ViewPropertyAnimator[] array;

        int index;

        boolean fusionMode;

        volatile boolean disposed;

        AnimatorDisposable(Observer<? super ViewPropertyAnimator> actual,
                ViewPropertyAnimator[] array) {
            this.actual = actual;
            this.array = array;
        }

        @Override
        public int requestFusion(int mode) {
            if ((mode & SYNC) != 0) {
                fusionMode = true;
                return SYNC;
            }
            return NONE;
        }

        @Override
        public ViewPropertyAnimator poll() {
            int i = index;
            ViewPropertyAnimator[] a = array;
            if (i != a.length) {
                index = i + 1;
                return ObjectHelper.requireNonNull(a[i], "The array element is null");
            }
            return null;
        }

        @Override
        public boolean isEmpty() {
            return index == array.length;
        }

        @Override
        public void clear() {
            index = array.length;
        }

        @Override
        public void dispose() {
            disposed = true;
            ViewPropertyAnimator[] a = array;
            int n = a.length;
            for (int i = 0; i < n; i++) {
                ViewPropertyAnimator value = a[i];
                if (value == null) {
                    return;
                }
                value.cancel();
            }
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }

        void run() {
            ViewPropertyAnimator[] a = array;
            int n = a.length;

            for (int i = 0; i < n && !isDisposed(); i++) {
                ViewPropertyAnimator value = a[i];
                if (value == null) {
                    actual.onError(new NullPointerException("The " + i + "th element is null"));
                    return;
                }
                int finalI = i;
                value.withEndAction(() -> {
                    actual.onNext(value);
                    if(finalI == n){
                        actual.onComplete();
                    }
                });
            }
        }
    }
}
