package com.devstr.dao;

import com.devstr.model.Token;

import java.math.BigInteger;
import java.util.List;

public interface TokenDAO {

    /**
     * Create token for Project
     * @param projectId
     * @param serviceName
     * @param tokenEncode
     */
    BigInteger createToken(BigInteger projectId, String serviceName, String tokenEncode) throws Exception;

    /**
     * Get token by Project id
     * @param projectId
     * @return
     */
    List<Token> readTokenByProject(BigInteger projectId);

    /**
     * Update token
     * @param tokenId
     * @param tokenEncode
     */
    void updateToken(BigInteger tokenId, String tokenEncode);

    /**
     * Update service name
     * @param tokenId
     * @param serviceName
     */
    public void updateServiceName(BigInteger tokenId, String serviceName);

    String READ_TOKEN_ID_BY_PROJECT_ID = "SELECT OBJECT_ID FROM OBJREFERENCE WHERE REFERENCE=?";

}
