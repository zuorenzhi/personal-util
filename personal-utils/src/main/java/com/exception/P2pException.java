package com.exception;

/**
 * Description : [Exception及其子类，编译时即需要抛出在方法外，或者捕获处理]
 * Created on : 2018年01月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

public class P2pException extends Exception{
    public P2pException() {
        super();
    }

    public P2pException(String message) {
        super(message);
    }

    public P2pException(String message, Throwable cause) {
        super(message, cause);
    }

    public P2pException(Throwable cause) {
        super(cause);
    }

    protected P2pException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
