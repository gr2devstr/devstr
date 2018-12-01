CREATE OR REPLACE PACKAGE abstract_eav_pkg IS
  FUNCTION insert_object(a_object_type_id NUMBER, a_name VARCHAR2) RETURN NUMBER;
  PROCEDURE delete_object(a_object_id NUMBER);
  PROCEDURE insert_objreference(a_attrn_id NUMBER, a_object_id NUMBER, a_reference NUMBER);
  PROCEDURE delete_objreference(a_attrn_id NUMBER, a_object_id NUMBER, a_reference NUMBER);
  PROCEDURE insert_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER, a_value VARCHAR2);
  PROCEDURE insert_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, a_date DATE);
  PROCEDURE insert_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, a_list_value_id NUMBER);
  PROCEDURE update_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER, new_value VARCHAR2);
  PROCEDURE update_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, new_date DATE);
  PROCEDURE update_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, new_list_value_id NUMBER);
  FUNCTION select_object_name(id NUMBER) RETURN VARCHAR2;
  FUNCTION select_object_id(a_object_type_id NUMBER, a_name VARCHAR2) RETURN NUMBER;
  FUNCTION select_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER) RETURN VARCHAR2;
  FUNCTION select_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER) RETURN DATE;
  FUNCTION select_attribute_list_value_id(a_attrn_id NUMBER, a_object_id NUMBER) RETURN NUMBER;
  FUNCTION select_objreference_obj(a_attrn_id NUMBER, a_reference NUMBER) RETURN sys_refcursor;
  FUNCTION select_objreference_ref(a_attrn_id NUMBER, a_object_id NUMBER) RETURN sys_refcursor;
END abstract_eav_pkg;
/

CREATE OR REPLACE PACKAGE BODY abstract_eav_pkg IS
  FUNCTION insert_object(a_object_type_id NUMBER, a_name VARCHAR2) RETURN NUMBER IS
    l_object_id NUMBER;
    BEGIN
	  INSERT INTO OBJECTS(object_type_id, name) VALUES (a_object_type_id, a_name)
	  returning object_id into l_object_id;
	  RETURN l_object_id;
	EXCEPTION
	  WHEN OTHERS THEN
	    logger_pkg.log('ERROR', SQLCODE||' failed to insert');
	END;
  PROCEDURE delete_object(a_object_id NUMBER) IS
  BEGIN
      DELETE FROM OBJECTS WHERE OBJECT_ID = a_object_id;
	  logger_pkg.log('INFO', SQL%ROWCOUNT||' rows deleted');
	  EXCEPTION
	      WHEN OTHERS THEN
		     logger_pkg.log('ERROR', SQLCODE||' failed to delete');
  END;
  PROCEDURE insert_objreference(a_attrn_id NUMBER, a_object_id NUMBER, a_reference NUMBER) IS
  BEGIN
    INSERT INTO OBJREFERENCE(ATTRN_ID, OBJECT_ID, REFERENCE) VALUES(a_attrn_id, a_object_id, a_reference);
  EXCEPTION
	  WHEN OTHERS THEN
	    logger_pkg.log('ERROR', SQLCODE||' failed to insert');
  END;
  PROCEDURE delete_objreference(a_attrn_id NUMBER, a_object_id NUMBER, a_reference NUMBER) IS 
  BEGIN
    DELETE FROM OBJREFERENCE WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id AND REFERENCE = a_reference;
	  logger_pkg.log('INFO', SQL%ROWCOUNT||' rows deleted');
	EXCEPTION
	    WHEN OTHERS THEN
	       logger_pkg.log('ERROR', SQLCODE||' failed to delete');
  END;
  PROCEDURE insert_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER, a_value VARCHAR2) IS
  BEGIN
    INSERT INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES (a_attrn_id, a_object_id, a_value);
  END;
  PROCEDURE insert_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, a_date DATE) IS
  BEGIN
    INSERT INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, DATE_VALUE) VALUES (a_attrn_id, a_object_id, a_date);
    EXCEPTION
	  WHEN OTHERS THEN
	    logger_pkg.log('ERROR', SQLCODE||' failed to insert');
  END;
  PROCEDURE insert_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, a_list_value_id NUMBER) IS 
  BEGIN
    INSERT INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, LIST_VALUE_ID) VALUES (a_attrn_id, a_object_id, a_list_value_id);
  EXCEPTION
	  WHEN OTHERS THEN
	    logger_pkg.log('ERROR', SQLCODE||' failed to insert');
  END;
  PROCEDURE update_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER, new_value VARCHAR2) IS
  BEGIN
	UPDATE ATTRIBUTES SET VALUE = new_value WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id;
	logger_pkg.log('INFO', SQL%ROWCOUNT||' rows updated');
  END;
  PROCEDURE update_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, new_date DATE) IS
  BEGIN
    UPDATE ATTRIBUTES SET DATE_VALUE = new_date WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id;
	logger_pkg.log('INFO', SQL%ROWCOUNT||' rows updated');
  END;
  PROCEDURE update_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, new_list_value_id NUMBER) IS
  BEGIN
    UPDATE ATTRIBUTES SET LIST_VALUE_ID = new_list_value_id WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id;
	logger_pkg.log('INFO', SQL%ROWCOUNT||' rows updated');
  END;
  FUNCTION select_object_name(id NUMBER) RETURN VARCHAR2 IS
  res VARCHAR2(2000 BYTE);
  BEGIN
    SELECT NAME INTO res FROM OBJECTS WHERE OBJECT_ID = id;
	RETURN res;
  END;
  FUNCTION select_object_id(a_object_type_id NUMBER, a_name VARCHAR2) RETURN NUMBER IS
  res NUMBER;
  BEGIN
    SELECT object_id INTO res FROM OBJECTS WHERE object_type_id = a_object_type_id AND NAME = a_name;
	RETURN res;
  END;
  FUNCTION select_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER) RETURN VARCHAR2 IS
  res VARCHAR2(4000 BYTE);
  BEGIN
    SELECT VALUE INTO res FROM ATTRIBUTES WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id; 
    RETURN res;
  END;
  FUNCTION select_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER) RETURN DATE IS
  res VARCHAR2(4000 BYTE);
  BEGIN
    SELECT DATE_VALUE INTO res FROM ATTRIBUTES WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id; 
    RETURN res;
  END;
  FUNCTION select_attribute_list_value_id(a_attrn_id NUMBER, a_object_id NUMBER) RETURN NUMBER IS
  res VARCHAR2(4000 BYTE);
  BEGIN
    SELECT LIST_VALUE_ID INTO res FROM ATTRIBUTES WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id; 
    RETURN res;
  END;
  FUNCTION select_objreference_obj(a_attrn_id NUMBER, a_reference NUMBER) RETURN sys_refcursor IS
    c sys_refcursor;
  BEGIN
    OPEN c FOR SELECT OBJECT_ID FROM OBJREFERENCE 
	                           WHERE ATTRN_ID = a_attrn_id AND REFERENCE = a_reference;
	RETURN c;
  END;
  FUNCTION select_objreference_ref(a_attrn_id NUMBER, a_object_id NUMBER) RETURN sys_refcursor IS
  c sys_refcursor;
  BEGIN
    OPEN c FOR  SELECT REFERENCE FROM OBJREFERENCE 
	                            WHERE ATTRN_ID = a_attrn_id AND OBJECT_ID = a_object_id;
	RETURN c;
  END;
END abstract_eav_pkg;
/