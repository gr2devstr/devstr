CREATE OR REPLACE PACKAGE abstract_eav_pkg IS
  TYPE object_row IS OBJECTS%ROWTYPE;
  TYPE attribute_row IS ATTRIBUTES%ROWTYPE;
  TYPE objreference_row IS OBJREFERENCE%ROWTYPE;
  PROCEDURE insert_object(a_object_type_id NUMBER, a_name VARCHAR2);
  PROCEDURE delete_object(a_object_id NUMBER);
  PROCEDURE insert_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER, a_value VARCHAR2);
  PROCEDURE insert_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, a_date DATE);
  PROCEDURE insert_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, a_list_value_id NUMBER);
  PROCEDURE update_attribute(a_attrn_id NUMBER, a_object_id NUMBER, new_value VARCHAR2);
  PROCEDURE update_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, new_date DATE);
  PROCEDURE update_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, new_list_value_id NUMBER);
  FUNCTION select_object(id NUMBER) RETURN object_row;
  FUNCTION select_object(a_name VARCHAR2) RETURN object_row;
  FUNCTION select_attributes(a_object_id NUMBER) RETURN sys_refcursor;
  FUNCTION select_attribute(a_attrn_id NUMBER, a_object_id NUMBER) RETURN attribute_row;
END abstract_eav_pkg;
/