package com.common.rr_lib.utils;

import com.common.rr_lib.retry.RetryWithDelay;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by muhanxi on 18/1/31.
 *
 */
public final class CommonUtils {


    public static <T> void subscribe(Flowable<T> flowable, Subscriber<T> subscriber) {
        flowable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(100,2))
                .subscribe(subscriber);
    }


}
