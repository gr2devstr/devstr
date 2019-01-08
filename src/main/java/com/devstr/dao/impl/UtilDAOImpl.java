package com.devstr.dao.impl;

import com.devstr.dao.UtilDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;

@Transactional
@Repository
public class UtilDAOImpl extends AbstractDAOImpl implements UtilDAO {

    @Override
    public boolean checkObjectType(BigInteger objectTypeId, BigInteger objectId) {
        return checkObjectTypeById(objectTypeId, objectId) == 1;
    }

}
