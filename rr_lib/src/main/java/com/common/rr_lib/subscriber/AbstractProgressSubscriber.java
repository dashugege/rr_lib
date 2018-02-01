package com.common.rr_lib.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InputStream;

/**
 * Created by muhanxi on 18/2/1.
 */

public abstract class AbstractProgressSubscriber implements Subscriber<InputStream> {

//    protected abstract void onStartDownload();
//
    protected abstract void onProgress(int progress);
//
//    protected abstract void onFinishDownload();
//
//    protected abstract void onFail(String errorInfo);


    @Override
    public void onSubscribe(Subscription s) {


    }

    @Override
    public void onNext(InputStream inputStream) {

        System.out.println("inputStream = " + inputStream);
        onProgress(1);


    }

    @Override
    public void onError(Throwable t) {
        System.out.println("inputStream t = " + t.getMessage());

    }

    @Override
    public void onComplete() {

    }
}
