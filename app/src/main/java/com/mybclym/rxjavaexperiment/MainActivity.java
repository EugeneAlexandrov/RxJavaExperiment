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

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> observableFrom = Observable.from(new String[]{"one", "two", "three"});
        Observer<String> observerFrom = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }
        };

        Observable<Integer> observableRange = Observable.range(10, 4);
        Observer<Integer> observerRange = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, s.toString());
            }
        };

        Observable<Long> observableInterval = Observable.interval(500, TimeUnit.MILLISECONDS);
        Observer<Long> observerInterval = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Long s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        // оборачиваем долгий метод в onCallable
//        Observable.fromCallable(new CallableLongAction("5"))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "onNext" + integer);
//                    }
//                });

        Observable<Integer> observableMap = Observable
                .from(new String[]{"1", "2", "3", "4", "5", "6"})
                .map(stringToInteger);
        Observer<Integer> observerMap = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable<List<Integer>> observableBuffer = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8})
                .buffer(3);
        Observer<List<Integer>> observerBuffer = new Observer<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(List<Integer> s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable<Integer> observableTake = Observable
                .from(new Integer[]{5, 6, 7, 8, 9})
                .take(3);

        Observer<Integer> observerTake = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable<Integer> observableSkip = Observable
                .from(new Integer[]{5, 6, 7, 8, 9})
                .skip(2);

        Observable<Integer> observableDistinct = Observable
                .from(new Integer[]{5, 9, 7, 5, 8, 6, 7, 8, 9})
                .distinct();

        Observable<String> observableFilter = Observable
                .from(new String[]{"15", "27", "34", "46", "52", "63"})
                .filter(filterFiveOnly);
        Observer<String> observerFilter = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable<Integer> observableMerge = Observable
                .from(new Integer[]{1, 2, 3})
                .mergeWith(Observable.from(new Integer[]{6, 7, 8, 9}));
        Observer<Integer> observerMerge = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable<String> observableZip = Observable
                .from(new Integer[]{1, 2, 3})
                .zipWith(Observable.from(new String[]{"One", "Two", "Three"}), zipIntWithString);
        Observer<String> observerZip = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable<Integer> observableTakeUntil = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8})
                .takeUntil(isFive);
        Observer<Integer> observerTakeUntil = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable<Boolean> observableAll = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8})
                .all(lessThanTen);
        Observer<Boolean> observerAll = new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Boolean s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

//        observableFrom.subscribe(observerFrom);
//        observableRange.subscribe(observerRange);
//        observableInterval.subscribe(observerInterval);
//        observableMap.subscribe(observerMap);
//        observableBuffer.subscribe(observerBuffer);
//        observableTake.subscribe(observerTake);
//        observableSkip.subscribe(observerTake);
//        observableDistinct.subscribe(observerTake);
//        observableFilter.subscribe(observerFilter);
//        observableMerge.subscribe(observerMerge);
//        observableZip.subscribe(observerZip);
//        observableTakeUntil.subscribe(observerTakeUntil);
        observableAll.subscribe(observerAll);


    }

    Func1<String, Integer> stringToInteger = new Func1<String, Integer>() {
        @Override
        public Integer call(String s) {
            return Integer.parseInt(s);
        }
    };

    Func1<String, Boolean> filterFiveOnly = new Func1<String, Boolean>() {
        @Override
        public Boolean call(String s) {
            return s.contains("5");
        }
    };

    Func2<Integer, String, String> zipIntWithString = new Func2<Integer, String, String>() {
        @Override
        public String call(Integer i, String s) {
            return s + ": " + i;
        }
    };

    Func1<Integer, Boolean> isFive = new Func1<Integer, Boolean>() {
        @Override
        public Boolean call(Integer i) {
            return i == 5;
        }
    };

    Func1<Integer, Boolean> lessThanTen = new Func1<Integer, Boolean>() {
        @Override
        public Boolean call(Integer i) {
            return i < 10;
        }
    };
}