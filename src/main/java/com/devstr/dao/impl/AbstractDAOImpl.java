package com.devstr.dao.impl;

import com.devstr.dao.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

@Transactional
@Repository
abstract class AbstractDAOImpl implements AbstractDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public BigInteger createObject(BigInteger typeId, String name) {
        return jdbcTemplate.queryForObject(CREATE_OBJECT, new Object[]{typeId, name}, BigInteger.class);
    }

    @Override
    public String readObjectNameById(BigInteger objectId) {
        return jdbcTemplate.queryForObject(READ_OBJECT_BY_ID, new Object[]{objectId}, String.class);
    }

    @Override
    public BigInteger readObjectIdByName(BigInteger typeId, String name) {
        return jdbcTemplate.queryForObject(READ_OBJECT_BY_ID, new Object[]{typeId, name}, BigInteger.class);
    }

    @Override
    public void deleteObjectById(BigInteger objectId) {
        jdbcTemplate.update(DEL_OBJECT_BY_ID, objectId);
    }

    @Override
    public void createAttributeValue(BigInteger attrnId, BigInteger objectId, String value) {
        jdbcTemplate.update(CREATE_ATTR_VALUE, new Object[]{attrnId, objectId, value});
    }

    @Override
    public void createAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value) {
        jdbcTemplate.update(CREATE_ATTR_DATE_VALUE, new Object[]{attrnId, objectId, value});
    }

    @Override
    public void createAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId) {
        jdbcTemplate.update(CREATE_ATTR_LIST_VALUE, new Object[]{attrnId, objectId, listValueId});
    }

    @Override
    public String readAttributeValue(BigInteger attrnId, BigInteger objectId) {
        return jdbcTemplate.queryForObject(READ_ATTR_VALUE, new Object[]{attrnId, objectId}, String.class);
    }

    @Override
    public Date readAttributeDateValue(BigInteger attrnId, BigInteger objectId) {
        return jdbcTemplate.queryForObject(READ_ATTR_DATE_VALUE, new Object[]{attrnId, objectId}, Date.class);
    }

    @Override
    public String readAttributeListValue(BigInteger attrnId, BigInteger objectId) {
        return jdbcTemplate.queryForObject(READ_ATTR_LIST_VALUE, new Object[]{attrnId, objectId}, String.class);
    }

    @Override
    public void updateAttributeValue(BigInteger attrnId, BigInteger objectId, String value) {
        jdbcTemplate.update(UPDATE_ATTR_VALUE, new Object[]{attrnId, objectId, value});
    }

    @Override
    public void updateAttributeDateValue(BigInteger attrnId, BigInteger objectId, Date value) {
        jdbcTemplate.update(UPDATE_ATTR_VALUE, new Object[]{attrnId, objectId, value});
    }

    @Override
    public void updateAttributeListValue(BigInteger attrnId, BigInteger objectId, BigInteger listValueId) {
        jdbcTemplate.update(UPDATE_ATTR_VALUE, new Object[]{attrnId, objectId, listValueId});
    }

    @Override
    public void createObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId) {
        jdbcTemplate.update(CREATE_OBJ_REFERENCE, new Object[]{attrnId, objectId, referenceId});
    }

    @Override
    public Collection<BigInteger> readObjectReferences(BigInteger attrnId, BigInteger objectId) {
        return jdbcTemplate.queryForList(READ_OBJ_REFERENCE_REF, new Object[]{attrnId, objectId}, BigInteger.class);
    }

    @Override
    public BigInteger readObjectByReference(BigInteger attrnId, BigInteger referenceId) {
        return jdbcTemplate.queryForObject(READ_OBJ_REFERENCE_OBJ, new Object[]{attrnId, referenceId}, BigInteger.class);
    }

    @Override
    public void deleteObjectReference(BigInteger attrnId, BigInteger objectId, BigInteger referenceId) {
        jdbcTemplate.update(DEL_OBJ_REFERENCE, new Object[]{attrnId, objectId, referenceId});
    }

}
