package com.devstr.dao;

import com.devstr.model.Token;

public interface TokenDAO {

    void createToken(int projectId, String tokensName,String serviceName,String tokenEncode);

    Token readTokenByProject(int projectId);

    void updateToken(int projectId, String serviceName,String tokenEncode);

    String CREATE_TOKEN= "BEGIN" +
            "INSERT INTO OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID) " +
            "            VALUES (OBJ_ID.nextval, ?, 6); " +
            "INSERT ALL " +
            "            INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (22, OBJ_ID.currval, ?)--input token name " +
            "            INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (24, OBJ_ID.currval, ?)--input encode token " +
            "            INTO OBJREFERENCE (ATTRN_ID, REFERENCE, OBJECT_ID) VALUES (23, ?, OBJ_ID.currval)--input project id " +
            "            SELECT * FROM dual; " +
            "END;";

    String READ_TOKEN_BY_PROJECT_ID = "SELECT o.NAME AS PROJECT_ID, a.VALUE AS TOKEN_NAME, b.VALUE AS TOKEN " +
            "FROM OBJECTS o, ATTRIBUTES a,  ATTRIBUTES b, OBJREFERENCE r " +
            "WHERE o.OBJECT_ID = ? " +
            "AND o.OBJECT_ID = r.REFERENCE " +
            "AND r.OBJECT_ID = a.OBJECT_ID " +
            "AND a.object_id = b.object_id " +
            "AND a.attrn_id = 22 " +
            "AND b.attrn_id = 24";

    String UPDATE_TOKEN = "UPDATE ATTRIBUTES " +
            "    SET ATTRIBUTES.VALUE = ? " +
            "    WHERE ATTRIBUTES.ATTRN_ID = 24 " +
            "    AND ATTRIBUTES.OBJECT_ID IN (SELECT " +
            "                                a.OBJECT_ID " +
            "                                FROM ATTRIBUTES a, OBJECTS o " +
            "                                WHERE a.ATTRN_ID = 22 " +
            "                                AND a.VALUE = ?  " +
            "                                AND o.OBJECT_ID = ? );";


}
