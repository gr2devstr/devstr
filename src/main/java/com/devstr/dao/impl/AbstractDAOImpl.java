package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.AbstractDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Repository
abstract class AbstractDAOImpl implements AbstractDAO {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(AbstractDAOImpl.class.getName());

    private static final String ABSTRACT_PKG = "abstract_eav_pkg";
    private static final String OBJECT_ID = "a_object_id";
    private static final String OBJECT_TYPE_ID = "a_object_type_id";
    private static final String OBJECT_NAME = "a_name";
    private static final String PARENT_ID = "a_parent_id";
    private static final String ATTRN_ID = "a_attrn_id";
    private static final String REFERENCE = "a_reference";
    private static final String RETURN = "return";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public BigInteger createObject(BigInteger typeId, String name) {
        Map<String, Object> in = new HashMap<>();
        in.put(OBJECT_TYPE_ID, typeId);
        in.put(OBJECT_NAME, name);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("insert_object");
            return call.executeFunction(BigDecimal.class, in).toBigInteger();
        } catch (DataAccessException exc1) {
            String message = "Failed to create object, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'insert_object' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public BigInteger createObjectWithParent(BigInteger typeId, BigInteger parentId, String name) {
        Map<String, Object> in = new HashMap<>();
        in.put(OBJECT_TYPE_ID, typeId);
        in.put(PARENT_ID, parentId);
        in.put(OBJECT_NAME, name);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("insert_object_with_parent");
            return call.executeFunction(BigDecimal.class, in).toBigInteger();
        } catch (DataAccessException exc1) {
            String message = "Failed to create object with parent, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'insert_object_with_parent' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public String readObjectNameById(BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put("id", objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_object_name");
            return call.executeFunction(String.class, in);
        } catch (DataAccessException exc1) {
            String message = "Failed to read object name by ID, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_object_name' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public BigInteger readObjectIdByName(BigInteger typeId, String name) {
        Map<String, Object> in = new HashMap<>();
        in.put(OBJECT_TYPE_ID, typeId);
        in.put(OBJECT_NAME, name);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_object_id");
            return call.executeFunction(BigDecimal.class, in).toBigInteger();
        } catch (DataAccessException exc1) {
            String message = "Failed to read object ID by name, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_object_id' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public BigInteger readParentId(BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put("id", objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_parent_id");
            return call.executeFunction(BigDecimal.class, in).toBigInteger();
        } catch (DataAccessException exc1) {
            String message = "Failed to read parent ID by object ID, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_parent_id' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void deleteObjectById(BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put(OBJECT_ID, objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("delete_object");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to delete object by ID, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'delete_object' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void createAttributeValue(BigInteger attrnId, BigInteger objectId, String value) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put("a_value", value);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("insert_attribute_value");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to insert attribute value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'insert_attribute_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void createAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put("a_date", value);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("insert_attribute_date_value");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to insert attribute date value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'insert_attribute_date_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void createAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put("a_list_value_id", listValueId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("insert_attribute_list_value");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to insert attribute list value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'insert_attribute_list_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public String readAttributeValue(BigInteger attrnId, BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_attribute_value");
            return call.executeFunction(String.class, in);
        } catch (DataAccessException exc1) {
            String message = "Failed to read attribute value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_attribute_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public Date readAttributeDateValue(BigInteger attrnId, BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_attribute_date_value");
            return call.executeFunction(Date.class, in);
        } catch (DataAccessException exc1) {
            String message = "Failed to read attribute date value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_attribute_date_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public String readAttributeListValue(BigInteger attrnId, BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_attr_list_value_name");
            return call.executeFunction(String.class, in);
        } catch (DataAccessException exc1) {
            String message = "Failed to read attribute list value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_attr_list_value_name' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void updateAttributeValue(BigInteger attrnId, BigInteger objectId, String value) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put("new_value", value);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("update_attribute_value");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to update attribute value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'update_attribute_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void updateAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put("new_value", value);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("update_attribute_date_value");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to update attribute date value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'update_attribute_date_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void updateAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put("new_list_value_id", listValueId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("update_attribute_list_value");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to update attribute list value, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'update_attribute_list_value' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void createObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put(REFERENCE, referenceId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("insert_objreference");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to create object reference, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'insert_objreference' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public Collection<BigInteger> readObjectReferences(BigInteger attrnId, BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_objreference_ref")
                    .declareParameters(new SqlOutParameter(RETURN, OracleTypes.CURSOR,
                            (rs, i) -> BigInteger.valueOf(rs.getLong(1))));
            Map<String, Object> resultMap = call.execute(in);
            return (Collection<BigInteger>) resultMap.get(RETURN);
        } catch (DataAccessException exc1) {
            String message = "Failed to read object reference, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_objreference_ref' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public Collection<BigInteger> readObjectByReference(BigInteger attrnId, BigInteger referenceId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(REFERENCE, referenceId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("select_objreference_obj")
                    .declareParameters(new SqlOutParameter(RETURN, OracleTypes.CURSOR,
                            (rs, i) -> BigInteger.valueOf(rs.getLong(1))));
            Map<String, Object> resultMap = call.execute(in);
            return (Collection<BigInteger>) resultMap.get(RETURN);
        } catch (DataAccessException exc1) {
            String message = "Failed to read object by reference, parameters" + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute function 'select_objreference_obj' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public void deleteObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId) {
        Map<String, Object> in = new HashMap<>();
        in.put(ATTRN_ID, attrnId);
        in.put(OBJECT_ID, objectId);
        in.put(REFERENCE, referenceId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withProcedureName("delete_objreference");
            call.execute(in);
        } catch (DataAccessException exc1) {
            String message = "Failed to delete object reference, parameters" + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'delete_objreference' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

    @Override
    public Integer checkObjectTypeById(BigInteger objectTypeId, BigInteger objectId) {
        Map<String, Object> in = new HashMap<>();
        in.put(OBJECT_TYPE_ID, objectTypeId);
        in.put(OBJECT_ID, objectId);
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ABSTRACT_PKG)
                    .withFunctionName("check_obj_type");
            return call.executeFunction(BigDecimal.class, in).intValue();
        } catch (DataAccessException exc1) {
            String message = "Failed to check object type, parameters: " + in.toString();
            LOGGER.error(message, exc1);
            throw new DaoException(message, exc1);
        } catch (NullPointerException exc2) {
            String message = "Failed to execute procedure 'check_obj_type' from empty call, parameters: "
                    + in.toString();
            LOGGER.warn(message, exc2);
            throw new DaoException(message, exc2);
        }
    }

}
