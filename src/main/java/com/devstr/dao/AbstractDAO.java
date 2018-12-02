package com.devstr.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public interface AbstractDAO {

    /**
     * Creates new row in the OBJECTS table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION insert_object(a_object_type_id NUMBER, a_name VARCHAR2) RETURN NUMBER;
     *
     * @param typeId object's type ID in the DB (objects.object_type_id)
     * @param name   object's name in the DB (objects.name)
     */
    BigInteger createObject(BigInteger typeId, String name);

    /**
     * Reads object's name by ID from OBJECTS table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION select_object_name(id NUMBER) RETURN VARCHAR2;
     *
     * @param objectId object's ID in the DB (objects.object_id)
     */
    String readObjectNameById(BigInteger objectId);

    /**
     * Reads object's ID by name and object type ID from OBJECTS table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION select_object_id(a_object_type_id NUMBER, a_name VARCHAR2) RETURN NUMBER;
     *
     * @param typeId object's type ID in the DB (objects.object_type_id)
     * @param name   object's name in the DB (objects.name)
     */
    BigInteger readObjectIdByName(BigInteger typeId, String name);

    /**
     * Deletes object by ID from OBJECTS table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE delete_object(a_object_id NUMBER);
     *
     * @param objectId object's name in the DB (objects.name)
     */
    void deleteObjectById(BigInteger objectId);

    /**
     * Creates new attribute's value by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE insert_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER, a_value VARCHAR2);
     *
     * @param attrnId  attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId object's ID in the DB (attributes.object_id)
     * @param value    attributes's value in the DB (attributes.value)
     */
    void createAttributeValue(BigInteger attrnId, BigInteger objectId, String value);

    /**
     * Creates new attribute's date value by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE insert_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, a_date DATE);
     *
     * @param attrnId  attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId object's ID in the DB (attributes.object_id)
     * @param value    attributes's date value in the DB (attributes.date_value)
     */
    void createAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value);

    /**
     * Creates new attribute's list value id by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE insert_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, a_list_value_id NUMBER);
     *
     * @param attrnId     attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId    object's ID in the DB (attributes.object_id)
     * @param listValueId attributes's list value ID in the DB (attributes.list_value_id)
     */
    void createAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId);

    /**
     * Reads attribute's value by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION select_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER) RETURN VARCHAR2;
     *
     * @param attrnId  attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId object's ID in the DB (attributes.object_id)
     */
    String readAttributeValue(BigInteger attrnId, BigInteger objectId);

    /**
     * Reads attribute's date value by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION select_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER) RETURN DATE;
     *
     * @param attrnId  attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId object's ID in the DB (attributes.object_id)
     */
    Date readAttributeDateValue(BigInteger attrnId, BigInteger objectId);

    /**
     * Reads attribute's list value by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION select_attribute_list_value_id(a_attrn_id NUMBER, a_object_id NUMBER) RETURN NUMBER;
     *
     * @param attrnId  attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId object's ID in the DB (attributes.object_id)
     */
    String readAttributeListValue(BigInteger attrnId, BigInteger objectId);

    /**
     * Updates attribute's value by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE update_attribute_value(a_attrn_id NUMBER, a_object_id NUMBER, new_value VARCHAR2);
     *
     * @param attrnId  attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId object's ID in the DB (attributes.object_id)
     * @param value    attributes's value in the DB (attributes.value)
     */
    void updateAttributeValue(BigInteger attrnId, BigInteger objectId, String value);

    /**
     * Updates attribute's date value by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE update_attribute_date_value(a_attrn_id NUMBER, a_object_id NUMBER, new_date DATE);
     *
     * @param attrnId  attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId object's ID in the DB (attributes.object_id)
     * @param value    attributes's date value in the DB (attributes.value)
     */
    void updateAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value);

    /**
     * Updates attribute's list value ID by object ID and attrn ID in the ATTRIBUTES table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE update_attribute_list_value(a_attrn_id NUMBER, a_object_id NUMBER, new_list_value_id NUMBER);
     *
     * @param attrnId     attributes's name ID in the DB (attributes.attrn_id)
     * @param objectId    object's ID in the DB (attributes.object_id)
     * @param listValueId attributes's list value ID in the DB (attributes.list_value_id)
     */
    void updateAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId);

    /**
     * Creates object's reference by attrn ID, reference ID and object ID in the OBJREFERENCE table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE insert_objreference(a_attrn_id NUMBER, a_object_id NUMBER, a_reference NUMBER);
     *
     * @param attrnId     attributes's name ID in the DB (objreference.attrn_id)
     * @param referenceId object's ID for reference object in the DB (objreference.reference)
     * @param objectId    object's ID in the DB (objreference.object_id)
     */
    void createObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId);

    /**
     * Reads object's references by attrn ID and object ID in the OBJREFERENCE table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION select_objreference_ref(a_attrn_id NUMBER, a_object_id NUMBER) RETURN sys_refcursor;
     *
     * @param attrnId  attributes's name ID in the DB (objreference.attrn_id)
     * @param objectId object's ID in the DB (objreference.object_id)
     */
    Collection<BigInteger> readObjectReferences(BigInteger attrnId, BigInteger objectId);

    /**
     * Reads objects ID by reference ID and attrn ID in the OBJREFERENCE table from the DB
     * Use SpringJdbcCall for PL\SQL Function:
     * FUNCTION select_objreference_obj(a_attrn_id NUMBER, a_reference NUMBER) RETURN sys_refcursor;
     *
     * @param attrnId     attributes's name ID in the DB (objreference.attrn_id)
     * @param referenceId object's ID in the DB (objreference.object_id)
     */
    Collection<BigInteger> readObjectByReference(BigInteger attrnId, BigInteger referenceId);

    /**
     * Deletes object's reference by attrn ID, reference ID and object ID in the OBJREFERENCE table from the DB
     * Use SpringJdbcCall for PL\SQL Procedure:
     * PROCEDURE delete_objreference(a_attrn_id NUMBER, a_object_id NUMBER, a_reference NUMBER);
     *
     * @param attrnId     attributes's name ID in the DB (objreference.attrn_id)
     * @param objectId    object's ID in the DB (objreference.object_id)
     * @param referenceId object's ID for reference object in the DB (objreference.reference)
     */
    void deleteObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId);

}
