package com.devstr.logger;

public class LoggerFactoryImpl implements LoggerFactory {
    @Override
    public DevstrLogger getLogger(String name) {
        return new DevstrLogger(name);
    }
}
