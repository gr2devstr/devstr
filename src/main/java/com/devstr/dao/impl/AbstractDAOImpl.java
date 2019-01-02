package com.devstr.dao.impl;

import com.devstr.dao.AbstractDAO;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Repository
abstract class AbstractDAOImpl implements AbstractDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public BigInteger createObject(BigInteger typeId, String name) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("insert_object");
        Map<String, Object> in = new HashMap<>();
        in.put("a_object_type_id", typeId);
        in.put("a_name", name);
        return call.executeFunction(BigDecimal.class, in).toBigInteger();
    }

    @Override
    public BigInteger createObjectWithParent(BigInteger typeId, BigInteger parentId, String name) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("insert_object_with_parent");
        Map<String, Object> in = new HashMap<>();
        in.put("a_object_type_id", typeId);
        in.put("a_parent_id", parentId);
        in.put("a_name", name);
        return call.executeFunction(BigDecimal.class, in).toBigInteger();
    }

    @Override
    public String readObjectNameById(BigInteger objectId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_object_name");
        Map<String, Object> in = new HashMap<>();
        in.put("id", objectId);
        return call.executeFunction(String.class, in);
    }

    @Override
    public BigInteger readObjectIdByName(BigInteger typeId, String name) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_object_id");
        Map<String, Object> in = new HashMap<>();
        in.put("a_object_type_id", typeId);
        in.put("a_name", name);
        return call.executeFunction(BigDecimal.class, in).toBigInteger();
    }

    @Override
    public BigInteger readParentId(BigInteger id) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_parent_id");
        Map<String, Object> in = new HashMap<>();
        in.put("id", id);
        return call.executeFunction(BigDecimal.class, in).toBigInteger();
    }

    @Override
    public void deleteObjectById(BigInteger objectId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("delete_object");
        Map<String, Object> in = new HashMap<>();
        in.put("a_object_id", objectId);
        call.execute(in);
    }

    @Override
    public void createAttributeValue(BigInteger attrnId, BigInteger objectId, String value) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("insert_attribute_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("a_value", value);
        call.execute(in);
    }

    @Override
    public void createAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("insert_attribute_date_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("a_date", value);
        call.execute(in);
    }

    @Override
    public void createAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("insert_attribute_list_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("a_list_value_id", listValueId);
        call.execute(in);
    }

    @Override
    public String readAttributeValue(BigInteger attrnId, BigInteger objectId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_attribute_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        return call.executeFunction(String.class, in);
    }

    @Override
    public Date readAttributeDateValue(BigInteger attrnId, BigInteger objectId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_attribute_date_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        return call.executeFunction(Date.class, in);
    }

    @Override
    public String readAttributeListValue(BigInteger attrnId, BigInteger objectId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_attr_list_value_name");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        return call.executeFunction(String.class, in);
    }

    @Override
    public void updateAttributeValue(BigInteger attrnId, BigInteger objectId, String value) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("update_attribute_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("new_value", value);
        call.execute(in);
    }

    @Override
    public void updateAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("update_attribute_date_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("new_value", value);
        call.execute(in);
    }

    @Override
    public void updateAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("update_attribute_list_value");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("new_list_value_id", listValueId);
        call.execute(in);
    }

    @Override
    public void createObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("insert_objreference");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("a_reference", referenceId);
        call.execute(in);
    }

    @Override
    public Collection<BigInteger> readObjectReferences(BigInteger attrnId, BigInteger objectId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_objreference_ref")
                .declareParameters(new SqlOutParameter("return", OracleTypes.CURSOR,
                        new RowMapper<BigInteger>() {
                            @Override
                            public BigInteger mapRow(ResultSet resultSet, int i) throws SQLException {
                                return BigInteger.valueOf(resultSet.getLong(1));
                            }
                        }));
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        Map<String, Object> resultMap = call.execute(in);
        return (Collection<BigInteger>) resultMap.get("return");
    }

    @Override
    public Collection<BigInteger> readObjectByReference(BigInteger attrnId, BigInteger referenceId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("select_objreference_obj")
                .declareParameters(new SqlOutParameter("return", OracleTypes.CURSOR,
                        new RowMapper<BigInteger>() {
                            @Override
                            public BigInteger mapRow(ResultSet resultSet, int i) throws SQLException {
                                return BigInteger.valueOf(resultSet.getLong(1));
                            }
                        }));
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_reference", referenceId);
        Map<String, Object> resultMap = call.execute(in);
        return (Collection<BigInteger>) resultMap.get("return");
    }

    @Override
    public void deleteObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withProcedureName("delete_objreference");
        Map<String, Object> in = new HashMap<>();
        in.put("a_attrn_id", attrnId);
        in.put("a_object_id", objectId);
        in.put("a_reference", referenceId);
        call.execute(in);
    }

    @Override
    public Integer checkObjectTypeById(BigInteger objectTypeId, BigInteger objectId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("abstract_eav_pkg")
                .withFunctionName("check_obj_type");
        Map<String, Object> in = new HashMap<>();
        in.put("a_object_type_id", objectTypeId);
        in.put("a_object_id", objectId);
        return call.executeFunction(BigDecimal.class, in).intValue();
    }

}
