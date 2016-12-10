package com.erevacation.reactiveanimations.rxjavaanimator;

import android.view.ViewPropertyAnimator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.operators.observable.ObservableDoOnEach;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by Master Milivoje Kojadinović and
 * Padawan David Lleixà on 02/12/16.
 */

public class AnimatorObservableSource<T> implements ObservableSource {


    List<ViewPropertyAnimator> mPropertyAnimators;
    AtomicInteger numberOfAnimationsToRun;

    public AnimatorObservableSource() {
    }

    public AnimatorObservableSource<T> setAnimatorList(List<ViewPropertyAnimator> viewPropertyAnimator) {
        this.mPropertyAnimators = viewPropertyAnimator;
        return this;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Disposable subscribe() {
        return subscribe(
                Functions.emptyConsumer(), Functions.ERROR_CONSUMER, Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
            Action onComplete, Consumer<? super Disposable> onSubscribe) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");

        LambdaObserver<T> ls = new LambdaObserver<T>(onNext, onError, onComplete, onSubscribe);

        subscribe(ls);

        return ls;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Observable<T> doOnNext(Consumer<? super T> onNext) {
        return doOnEach(onNext, Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Observable<T> doOnComplete(Action onComplete) {
        return doOnEach(Functions.emptyConsumer(), Functions.emptyConsumer(), onComplete, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    private Observable<T> doOnEach(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete,
            Action onAfterTerminate) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        return RxJavaPlugins.onAssembly(new ObservableDoOnEach<T>(this, onNext, onError, onComplete, onAfterTerminate));
    }

    @Override
    public void subscribe(Observer observer) {
        ObjectHelper.requireNonNull(observer, "observer is null");
        numberOfAnimationsToRun = new AtomicInteger(mPropertyAnimators.size());
        for (int i = 0; i < mPropertyAnimators.size(); i++) {
            ViewPropertyAnimator propertyAnimator = mPropertyAnimators.get(i);
            propertyAnimator.withEndAction(() -> {
                        // Call onNext after every animation
                        observer.onNext(propertyAnimator);
                        // Once all animations are done, call onCompleted().
                        if (numberOfAnimationsToRun.decrementAndGet() == 0) {
                            observer.onComplete();
                        }
                    }
                                          );
        }
    }

    public void clear(){
        if(mPropertyAnimators == null){
            return;
        }
        numberOfAnimationsToRun = new AtomicInteger(mPropertyAnimators.size());
        for (int i = 0; i < mPropertyAnimators.size(); i++) {
            ViewPropertyAnimator propertyAnimator = mPropertyAnimators.get(i);
            propertyAnimator.cancel();
        }
    }
}
