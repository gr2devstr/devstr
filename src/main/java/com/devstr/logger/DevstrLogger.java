package com.devstr.logger;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.math.BigInteger;

public class DevstrLogger extends Log4JLogger {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public DevstrLogger(String name) {
        super(name);
    }

    @Override
    public void warn(Object message) {
        logInDB("WARN", message.toString());
        super.warn(message);
    }

    @Override
    public void error(Object message) {
        logInDB("ERROR", message.toString());
        super.error(message);
    }

    @Override
    public void fatal(Object message) {
        logInDB("FATAL", message.toString());
        super.fatal(message);
    }

    @Override
    public void trace(Object message, Throwable t) {
        if (DataAccessException.class.isAssignableFrom(t.getClass()))
            logInDB("TRACE", message + t.getMessage());
        super.trace(message, t);
    }

    @Override
    public void debug(Object message, Throwable t) {
        if (DataAccessException.class.isAssignableFrom(t.getClass()))
            logInDB("DEBUG", message + t.getMessage());
        super.debug(message, t);
    }

    @Override
    public void info(Object message, Throwable t) {
        if (DataAccessException.class.isAssignableFrom(t.getClass()))
            logInDB("INFO", message + t.getMessage());
        super.info(message, t);
    }

    @Override
    public void warn(Object message, Throwable t) {
        if (DataAccessException.class.isAssignableFrom(t.getClass()))
            logInDB("WARN", message + t.getMessage());
        super.warn(message, t);
    }

    @Override
    public void error(Object message, Throwable t) {
        if (DataAccessException.class.isAssignableFrom(t.getClass()))
            logInDB("ERROR", message + t.getMessage());
        super.error(message, t);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        if (DataAccessException.class.isAssignableFrom(t.getClass()))
            logInDB("FATAL", message + t.getMessage());
        super.fatal(message, t);
    }

    public void setDBLoggingLevel(String level) {
        jdbcTemplate.update("exec logger_pkg.set_level(?)", level);
    }

    public String getDBLoggingLevel() {
        return jdbcTemplate.queryForObject("select logger_pkg.get_level from dual", String.class);
    }

    public void setThreshold(BigInteger threshold) {
        jdbcTemplate.update("exec logger_pkg.set_threshold(?)", threshold);
    }

    public BigInteger getThreshold() {
        return jdbcTemplate.queryForObject("select logger_pkg.get_threshold from dual", BigInteger.class);
    }

    public void logDB(String level, String message) {
        logInDB(level, message);
    }

    public void setClearLog() {
        if (jdbcTemplate != null) {
            jdbcTemplate.execute("exec logger_pkg.set_clear_log");
        }
    }

    private void logInDB(String level, String message) {
        if (jdbcTemplate != null) {
            jdbcTemplate.update("exec logger_pkg.log(?,?)", level, message.substring(0, 1998));
        }
    }
}
