package com.devstr.logger;

import javax.sql.DataSource;

public class LoggerFactoryImpl implements LoggerFactory {
    @Override
    public DevstrLogger getLogger(String name) {
        return new DevstrLogger(name);
    }

    @Override
    public DevstrLogger getLogger(String name, DataSource dataSource) {
        return new DevstrLogger(name, dataSource);
    }
}
