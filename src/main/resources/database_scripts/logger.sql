BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE log_id_seq';
EXCEPTION
    WHEN OTHERS THEN
    IF SQLCODE != -2289 THEN
    RAISE;
    END IF;
END;
/
BEGIN
EXECUTE IMMEDIATE 'DROP TABLE LOGGER CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -942 THEN
    RAISE;
    END IF;
  END;
/

create SEQUENCE log_id_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE TABLE LOGGER
(
LOG_ID         NUMBER(10),
MSG_LEVEL      VARCHAR2(200 CHAR),
MESSAGE        VARCHAR2(1999 CHAR),
LOG_DATE       DATE,
CONSTRAINT CON_LOG_ID PRIMARY KEY (LOG_ID)
);

create or replace trigger Trigger_log_id
BEFORE INSERT
  on LOGGER
for each row
begin
    select log_id_seq.nextval
    into :new.LOG_ID
    from dual;
end;
/