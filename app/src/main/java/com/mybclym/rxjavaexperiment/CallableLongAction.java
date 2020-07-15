package com.mybclym.rxjavaexperiment;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableLongAction implements Callable<Integer> {
    private final String data;

    public CallableLongAction(String data) {
        this.data = data;
    }

    @Override
    public Integer call() throws Exception {
        return longAction(data);
    }

    private int longAction(String text) {
        log("longAction");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(text);
    }

    private void log(String longAction) {
        Log.d("TEST", longAction);
    }

}
