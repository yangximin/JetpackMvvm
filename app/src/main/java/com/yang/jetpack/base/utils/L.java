package com.yang.jetpack.base.utils;

import android.util.Log;

/**
 * Created by yxm on 2021/1/14.
 */
public class L {
    private static final boolean isDebug = true;

    private final static String TAG = "YANG--LOG";

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void exception(Exception e) {
        if (isDebug) {
            e.printStackTrace();
        }
    }

    public static void exception(Throwable throwable) {
        if (isDebug) {
            throwable.printStackTrace();
        }
    }
}
