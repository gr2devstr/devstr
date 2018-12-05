/*
Package for usage of devstr logger.

level - current minimum level, stores at table LOGGER_CONFIG,
threshold - maximum value of rows in table LOGGER, stores at LOGGER_CONFIG,
PROCEDURE log(a_msg_levelm, a_message) - add new log to table LOGGER, use two varchar2 args: level and message
get/set_level/threshold - getters and setters for global variables. Updates table LOGGER_CONFIG. 

*/

CREATE OR REPLACE PACKAGE logger_pkg AS
    level VARCHAR2(30) := 'INFO';
    threshold NUMBER(10) := 2000;
  PROCEDURE log(a_msg_level VARCHAR2, a_message VARCHAR2);
  FUNCTION get_level RETURN VARCHAR2;
  PROCEDURE set_level(new_level VARCHAR2);
  FUNCTION get_threshold RETURN NUMBER;
  PROCEDURE set_threshold(new_threshold NUMBER);
  PROCEDURE set_clear_log;
END logger_pkg;
/

CREATE OR REPLACE PACKAGE BODY logger_pkg AS
    p_size NUMBER(10) := 0;
  PROCEDURE initialize IS
  BEGIN
    SELECT C_VALUE INTO level FROM LOGGER_CONFIG WHERE CONFIG_ID = 1; /*LEVEL*/
    SELECT C_VALUE INTO threshold FROM LOGGER_CONFIG WHERE CONFIG_ID = 2;/*THRESHOLD*/
    SELECT C_VALUE INTO p_size FROM LOGGER_CONFIG WHERE CONFIG_ID = 3; /*SIZE*/
  END;
  PROCEDURE update_level(new_level VARCHAR2) IS
  BEGIN
    UPDATE LOGGER_CONFIG SET C_VALUE = new_level WHERE CONFIG_ID = 1;
	COMMIT;
  END;
  PROCEDURE update_threshold(new_threshold NUMBER) IS
  BEGIN
    UPDATE LOGGER_CONFIG SET C_VALUE = new_threshold WHERE CONFIG_ID = 2;
	COMMIT;
  END;
  PROCEDURE update_size(new_size NUMBER) IS
  BEGIN
    UPDATE LOGGER_CONFIG SET C_VALUE = new_size WHERE CONFIG_ID = 3;
	COMMIT;
  END;
  FUNCTION is_higher (a_msg_level VARCHAR2) RETURN BOOLEAN
  IS
  BEGIN
  IF NOT regexp_like(a_msg_level, '(^TRACE$)|(^DEBUG$)|(^INFO$)|(^WARN$)|(^ERROR$)|(^FATAL$)') THEN
    log('WARN', 'log level is illegal');
	RETURN FALSE;
  END IF;
  IF level = 'TRACE' THEN
    RETURN TRUE;
  ELSIF level = 'DEBUG'	THEN 
    IF a_msg_level = 'TRACE'THEN 
	  RETURN FALSE;
	ELSE
	  RETURN TRUE;
	END IF;
  ELSIF level = 'INFO' THEN
    IF regexp_like(a_msg_level, '(^TRACE$)|(^DEBUG$)') THEN 
	  RETURN FALSE;
	ELSE RETURN TRUE;
	END IF;
  ELSIF level = 'WARN' THEN 
    IF regexp_like(a_msg_level, '(^TRACE$)|(^DEBUG$)|(^INFO)') THEN
      RETURN FALSE;
    ELSE
      RETURN TRUE;
	END IF;
  ELSIF level = 'ERROR' THEN
    IF regexp_like(a_msg_level, '(^ERROR$)|(^FATAL$)') THEN
	  RETURN TRUE;
	ELSE
	  RETURN FALSE;
	END IF;
  ELSIF level = 'FATAL' THEN 
    IF a_msg_level = 'FATAL' THEN RETURN TRUE;
	ELSE RETURN FALSE;
	END IF;
  END IF;
  RETURN FALSE;
  END;
  PROCEDURE log(a_msg_level VARCHAR2, a_message VARCHAR2)
  IS
  BEGIN
      initialize;
      IF is_higher(a_msg_level) THEN
	    IF p_size < threshold THEN
	      INSERT INTO LOGGER(MSG_LEVEL, MESSAGE, LOG_DATE) VALUES (a_msg_level, a_message, SYSDATE);
		  COMMIT;
		  p_size := p_size + 1;
		  update_size(p_size);
		ELSE
		  DELETE FROM LOGGER WHERE LOG_ID = (select MIN(LOG_ID) FROM LOGGER);
		  INSERT INTO LOGGER(MSG_LEVEL, MESSAGE, LOG_DATE) VALUES (a_msg_level, a_message, SYSDATE);
		  COMMIT;
		END IF;
	  END IF;
  END;
  PROCEDURE set_level(new_level VARCHAR2)
  IS
  BEGIN
    IF regexp_like(new_level, '(^TRACE$)|(^DEBUG$)|(^INFO$)|(^WARN$)|(^ERROR$)|(^FATAL$)') THEN
	  level := new_level;
	  update_level(new_level);
	END IF;
  END;
  FUNCTION get_level RETURN VARCHAR2 IS
  BEGIN
    initialize;
    RETURN level;
  END;
  PROCEDURE set_threshold(new_threshold NUMBER) IS
  BEGIN
    IF new_threshold > 0 THEN
	  IF new_threshold < threshold THEN
	    WHILE p_size > new_threshold LOOP
		  DELETE FROM LOGGER WHERE LOG_ID = (select MIN(LOG_ID) FROM LOGGER);
		  p_size := p_size -1;
		END LOOP;
		update_size(p_size);
		update_threshold(new_threshold);
	  END IF;
      threshold := new_threshold;
	  update_threshold(new_threshold);
	END IF;
  END;
  FUNCTION get_threshold RETURN NUMBER IS
  BEGIN
    initialize;
    RETURN threshold;
  END;
  PROCEDURE set_clear_log IS
  BEGIN
    DELETE FROM LOGGER;
	p_size := 0;
	update_size(p_size);
	EXECUTE IMMEDIATE 'DROP SEQUENCE log_id_seq';
	EXECUTE IMMEDIATE 'CREATE SEQUENCE log_id_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
	COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
    IF SQLCODE != -2289 THEN
      RAISE;
	ELSE
	  EXECUTE IMMEDIATE 'CREATE SEQUENCE log_id_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
    END IF;
  END;
END logger_pkg;
/