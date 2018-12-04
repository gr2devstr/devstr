package com.devstr;

import com.devstr.logger.LoggerFactory;
import com.devstr.logger.LoggerFactoryImpl;
import com.devstr.services.ServiceFactory;
import com.devstr.services.impl.ServiceFactoryImpl;

public class DevstrFactoryManager {

    public static LoggerFactory getLoggerFactory() {
        return new LoggerFactoryImpl();
    }

    public static ServiceFactory getServiceFactory() { return new ServiceFactoryImpl(); }
}
