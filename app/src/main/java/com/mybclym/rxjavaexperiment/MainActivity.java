package com.mybclym.rxjavaexperiment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TEST";
    long start_time = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                log("observer1 onNext value = " + aLong);
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                log("observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10);

        final PublishSubject<Long> subject = PublishSubject.create();

        log("subject subscribe");
        observable.subscribe(subject);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer1 subscribe");
                subject.subscribe(observer1);
            }
        }, 3500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer2 subscribe");
                subject.subscribe(observer2);
            }
        }, 5500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                subject.onNext(100L);
            }
        }, 7500);
    }

    public void log(String msg) {
        String time = String.valueOf(System.currentTimeMillis() - start_time);
        Log.d(TAG, time + " " + msg);
    }
}