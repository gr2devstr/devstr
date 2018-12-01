CREATE OR REPLACE PACKAGE logger_pkg AS
    level VARCHAR2(30) := 'INFO';
    threshold NUMBER(10) := 2000;
  PROCEDURE log(a_msg_level VARCHAR2, a_message VARCHAR2);
  FUNCTION get_level RETURN VARCHAR2;
  PROCEDURE set_level(new_level VARCHAR2);
  FUNCTION get_threshold RETURN NUMBER;
  PROCEDURE set_threshold(new_threshold NUMBER);
END logger_pkg;
/

CREATE OR REPLACE PACKAGE BODY logger_pkg AS
    p_size NUMBER(10) := 0;
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
      IF is_higher(a_msg_level) THEN
	    IF p_size < threshold THEN
	      INSERT INTO LOGGER(MSG_LEVEL, MESSAGE, LOG_DATE) VALUES (a_msg_level, a_message, SYSDATE);
		  p_size := p_size + 1;
		ELSE
		  DELETE FROM LOGGER WHERE LOG_DATE = (select MIN(LOG_DATE) FROM LOGGER);
		  INSERT INTO LOGGER(MSG_LEVEL, MESSAGE, LOG_DATE) VALUES (a_msg_level, a_message, SYSDATE);
		END IF;
	  END IF;
  END;
  PROCEDURE set_level(new_level VARCHAR2)
  IS
  BEGIN
    IF regexp_like(new_level, '(^TRACE$)|(^DEBUG$)|(^INFO$)|(^WARN$)|(^ERROR$)|(^FATAL$)') THEN
	  level := new_level;
	END IF;
  END;
  FUNCTION get_level RETURN VARCHAR2 IS
  BEGIN
    RETURN level;
  END;
  PROCEDURE set_threshold(new_threshold NUMBER) IS
  BEGIN
    IF new_threshold > 0 THEN
	  IF new_threshold < threshold THEN
	    WHILE p_size > new_threshold LOOP
		  DELETE FROM LOGGER WHERE LOG_DATE = (select MIN(LOG_DATE) FROM LOGGER);
		  p_size := p_size -1;
		END LOOP;
	  END IF;
      threshold := new_threshold;
	END IF;
  END;
  FUNCTION get_threshold RETURN NUMBER IS
  BEGIN
    RETURN threshold;
  END;
END logger_pkg;
/