package com.devstr.logger;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class DevstrLogger extends Log4JLogger {

    private static String INSERT_LOG = "INSERT INTO LOGGER(MSG_LEVEL, MESSAGE, LOG_DATE) VALUES(?, ?, SYSDATE)";
    private static String CLEAR_LOG = "DELETE FROM LOGGER";
    private static String DROP_SEQ = "DROP SEQUENCE log_id_seq";
    private static String CREATE_SEQ = "CREATE SEQUENCE log_id_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public DevstrLogger(String name) {
        super(name);
    }

    @Override
    public void trace(Object message, Throwable t) {
        if (SQLException.class.isAssignableFrom(t.getClass()))
            logInDB("TRACE", message + t.getMessage());
        super.trace(message, t);
    }

    @Override
    public void debug(Object message, Throwable t) {
        if (SQLException.class.isAssignableFrom(t.getClass()))
            logInDB("DEBUG", message + t.getMessage());
        super.debug(message, t);
    }

    @Override
    public void info(Object message, Throwable t) {
        if (SQLException.class.isAssignableFrom(t.getClass()))
            logInDB("INFO", message + t.getMessage());
        super.info(message, t);
    }

    @Override
    public void warn(Object message, Throwable t) {
        if (SQLException.class.isAssignableFrom(t.getClass()))
            logInDB("WARNING", message + t.getMessage());
        super.warn(message, t);
    }

    @Override
    public void error(Object message, Throwable t) {
        if (SQLException.class.isAssignableFrom(t.getClass()))
            logInDB("ERROR", message + t.getMessage());
        super.error(message, t);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        if (SQLException.class.isAssignableFrom(t.getClass()))
            logInDB("FATAL", message + t.getMessage());
        super.fatal(message, t);
    }

    public void logDB(String level, String message) {
        logInDB(level, message);
    }

    public void setClearLog() {
        if (jdbcTemplate != null) {
            jdbcTemplate.execute(CLEAR_LOG);
            jdbcTemplate.execute(DROP_SEQ);
            jdbcTemplate.execute(CREATE_SEQ);
        }
    }

    private void logInDB(String level, String message) {
        if (jdbcTemplate != null) {
            jdbcTemplate.update(INSERT_LOG, level, message.substring(0, 1998));
        }
    }
}
