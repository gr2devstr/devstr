package com.devstr;

import com.devstr.logger.LoggerFactory;
import com.devstr.logger.LoggerFactoryImpl;

public class DevstrFactoryManager {

    public static LoggerFactory getLoggerFactory() {
        return new LoggerFactoryImpl();
    }
}
