package com.devstr.dao;

import java.math.BigInteger;

public interface UtilDAO {

    /**
     * Checks object type ID by object ID in the OBJECTS table from the DB
     * Returns TRUE if object type ID in the OBJECTS from the DB equals @param objectTypeId,
     * else returns FALSE.
     *
     * @param objectTypeId attributes's name ID in the DB (objreference.attrn_id)
     * @param objectId     object's ID in the DB (objreference.object_id)
     */
    boolean checkObjectType(BigInteger objectTypeId, BigInteger objectId);

}
