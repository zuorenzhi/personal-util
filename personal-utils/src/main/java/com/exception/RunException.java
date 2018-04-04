package com.exception;

/**
 * Description : [RuntimeException及其子类,在编译时 可以被直接抛出]
 * Created on : 2018年01月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

public class RunException extends RuntimeException {
    public RunException() {
        super();
    }

    public RunException(String message) {
        super(message);
    }

    public RunException(String message, Throwable cause) {
        super(message, cause);
    }

    public RunException(Throwable cause) {
        super(cause);
    }

    protected RunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
