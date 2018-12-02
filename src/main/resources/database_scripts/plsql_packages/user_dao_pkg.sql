CREATE OR REPLACE PACKAGE user_dao AS
  TYPE full_user IS RECORD(user_id NUMBER, username VARCHAR2(100 CHAR), first_name VARCHAR2(100 CHAR), last_name VARCHAR2(100 CHAR), email VARCHAR2(100 CHAR),
                              user_role_id NUMBER, creation_date DATE, password VARCHAR2(100 CHAR), status_id NUMBER,
							  active_project_id NUMBER);
  TYPE basic_user IS RECORD(user_id NUMBER, username VARCHAR2(100 CHAR), first_name VARCHAR2(100 CHAR), last_name VARCHAR2(100 CHAR), email VARCHAR2(100 CHAR),
                              user_role_id NUMBER, password VARCHAR2(100 CHAR), status_id NUMBER);
  PROCEDURE insert_user(username VARCHAR2, first_name VARCHAR2, last_name VARCHAR2, email VARCHAR2,
                       user_role_id NUMBER, password VARCHAR2, status_id NUMBER);
  PROCEDURE update_user_first_name(user_id NUMBER, new_first_name VARCHAR2);
  PROCEDURE update_user_last_name(user_id NUMBER, new_last_name VARCHAR2);
  PROCEDURE update_user_email(user_id NUMBER, new_email VARCHAR2);
  PROCEDURE update_user_role(user_id NUMBER, new_role_id NUMBER);
  PROCEDURE update_user_password(user_id NUMBER, new_password NUMBER);
  PROCEDURE activate_user(user_id NUMBER);
  PROCEDURE inactvate_user(user_id NUMBER);
  PROCEDURE delete_user(user_id NUMBER);
  FUNCTION select_full_user_by_id(user_id NUMBER) RETURN full_user;
  FUNCTION select_full_user_by_login(login VARCHAR2) RETURN full_user;
  FUNCTION select_basic_user_by_id(user_id NUMBER) RETURN basic_user;
  FUNCTION select_basic_user_by_login(login VARCHAR2) RETURN basic_user;
END user_dao;
/

CREATE OR REPLACE PACKAGE BODY user_dao AS
  PROCEDURE insert_user(username VARCHAR2, first_name VARCHAR2, last_name VARCHAR2, email VARCHAR2,
                       user_role_id NUMBER, password VARCHAR2, status_id NUMBER) IS
  l_object_id NUMBER;
  BEGIN
    l_object_id := abstract_eav_pkg.insert_object(1, username);
	abstract_eav_pkg.insert_attribute_value(1, l_object_id, first_name); /*first_name*/
	abstract_eav_pkg.insert_attribute_value(2, l_object_id, last_name); /*last_name*/
	abstract_eav_pkg.insert_attribute_value(3, l_object_id, email); /*email*/
	abstract_eav_pkg.insert_attribute_list_value(4, l_object_id, user_role_id); /*user_role*/
	abstract_eav_pkg.insert_attribute_date_value(5, l_object_id, SYSDATE); /*creation_date*/
	abstract_eav_pkg.insert_attribute_value(6, l_object_id, password); /*password*/
	abstract_eav_pkg.insert_attribute_list_value(7, l_object_id, status_id); /*status*/
	EXCEPTION
	  WHEN OTHERS THEN 
	    logger_pkg.log('ERROR', SQLCODE||' Error while inserting user');
  END;
  PROCEDURE update_user_first_name(user_id NUMBER, new_first_name VARCHAR2) IS
  BEGIN
    abstract_eav_pkg.update_attribute_value(1, user_id, new_first_name); /*first_name*/
  END;
  PROCEDURE update_user_last_name(user_id NUMBER, new_last_name VARCHAR2) IS
  BEGIN
    abstract_eav_pkg.update_attribute_value(2, user_id, new_last_name); /*last_name*/
  END;
  PROCEDURE update_user_email(user_id NUMBER, new_email VARCHAR2) IS
  BEGIN
    abstract_eav_pkg.update_attribute_value(3, user_id, new_email); /*email*/
  END;
  PROCEDURE update_user_role(user_id NUMBER, new_role_id NUMBER) IS
  BEGIN
    abstract_eav_pkg.update_attribute_list_value(4, user_id, new_role_id); /*user_role*/
  END;
  PROCEDURE update_user_password(user_id NUMBER, new_password NUMBER) IS 
  BEGIN
    abstract_eav_pkg.update_attribute_value(6, user_id, new_password); /*password*/
  END;
  PROCEDURE activate_user(user_id NUMBER) IS
  BEGIN
    abstract_eav_pkg.update_attribute_list_value(7, user_id, 5); /*status, active*/
  END;
  PROCEDURE inactvate_user(user_id NUMBER) IS
  BEGIN
    abstract_eav_pkg.update_attribute_list_value(7, user_id, 6); /*status, inactive*/
  END;
  PROCEDURE delete_user(user_id NUMBER) IS
  BEGIN
    abstract_eav_pkg.delete_object(user_id);
  END;
  FUNCTION select_full_user_by_id(user_id NUMBER) RETURN full_user IS
  res full_user;
  BEGIN
    RETURN res;
  END;
  FUNCTION select_full_user_by_login(login VARCHAR2) RETURN full_user IS
  l_object_id NUMBER;
  BEGIN
    l_object_id := abstract_eav_pkg.select_object_id(1, login); /*user*/
    RETURN select_full_user_by_id(l_object_id);
  END;
  FUNCTION select_basic_user_by_id(user_id NUMBER) RETURN basic_user IS
  res basic_user;
  BEGIN
    RETURN res;
  END;
  FUNCTION select_basic_user_by_login(login VARCHAR2) RETURN basic_user IS
  l_object_id NUMBER;
  BEGIN
    l_object_id := abstract_eav_pkg.select_object_id(1, login); /*user*/
    RETURN select_basic_user_by_id(l_object_id);
  END;
END user_dao;
/