package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.UtilDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;

@Transactional
@Repository
public class UtilDAOImpl extends AbstractDAOImpl implements UtilDAO {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(UtilDAOImpl.class.getName());

    @Override
    public boolean checkObjectType(BigInteger objectTypeId, BigInteger objectId) {
        try {
            return checkObjectTypeById(objectTypeId, objectId) == 1;
        } catch (DataAccessException exc) {
            String message = "UtilDAOImpl.checkObjectTypeById(...), object type ID: " + objectTypeId +
                    ", object ID: " + objectId;
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

}
