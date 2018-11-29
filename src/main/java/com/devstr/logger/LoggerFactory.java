package com.devstr.logger;

import javax.sql.DataSource;

public interface LoggerFactory {

    DevstrLogger getLogger(String name);

    DevstrLogger getLogger(String name, DataSource dataSource);
}
