package com.devstr.dao.impl;

import com.devstr.dao.TokenDAO;
import com.devstr.model.Token;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.impl.TokenImpl;
import com.devstr.services.impl.TokenServiceImp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;


@Transactional
@Repository
public class TokenDAOImpl extends AbstractDAOImpl implements TokenDAO{

    @Override
    public BigInteger createToken(BigInteger projectId, String serviceName, String tokenEncode) throws Exception {

        TokenServiceImp tokenServiceImp = new TokenServiceImp();

        BigInteger tokenId = createObject(ObjectType.TOKEN.getId(),"TOKEN");
        createAttributeValue(AttributeID.SERVICE_NAME.getId(),tokenId,serviceName);
        createAttributeValue(AttributeID.TOKEN_CODE.getId(), tokenId, tokenServiceImp.encrypt(tokenEncode));
        createObjectReference(AttributeID.PROJECT.getId(), tokenId, projectId);
        return tokenId;
    }

    @Override
    public List<Token> readTokenByProject(BigInteger projectId) {

        List<Token> tokenList = new ArrayList<>();
        Collection<BigInteger> id_token = readObjectByReference(AttributeID.PROJECT.getId(), projectId);
        for (BigInteger token : id_token){
            tokenList.add(getObjIdByRef(token));
        }
        return tokenList;
    }

    @Override
    public void updateToken(BigInteger tokenId, String tokenEncode) {
        updateAttributeValue(AttributeID.TOKEN_CODE.getId(),tokenId, tokenEncode);

    }

    @Override
    public void updateServiceName(BigInteger tokenId, String serviceName) {
        updateAttributeValue(AttributeID.SERVICE_NAME.getId(),tokenId,serviceName);

    }

    public Token getObjIdByRef(BigInteger tokenId){
        return new TokenImpl.Builder(tokenId,
                readAttributeValue(AttributeID.SERVICE_NAME.getId(),tokenId),
                readAttributeValue(AttributeID.TOKEN_CODE.getId(),tokenId)).builder();
    }
}
