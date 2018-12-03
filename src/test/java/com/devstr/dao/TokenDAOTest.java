package com.devstr.dao;

import com.devstr.dao.impl.TokenDAOImpl;
import com.devstr.model.Token;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.impl.TokenImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@Ignore
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenDAOTest {

    @Autowired
    TokenDAOImpl tokenDAO = new TokenDAOImpl();

    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void createToken() throws Exception {
        Token token = new TokenImpl.Builder(BigInteger.valueOf(81), "git", "54utihhf").builder();
        BigInteger id = tokenDAO.createToken(token.getProjectId(),token.getServiceName(),token.getTokenEncode());
       // assertEquals(BigInteger.valueOf(501L),id);
    }

    @Test
    public void readToken(){
       List<Token> token = tokenDAO.readTokenByProject(BigInteger.valueOf(81L));
        assertEquals(BigInteger.valueOf(501L), token.get(0).getTokenId());
    }

    @Test
    public void updateToken(){
        tokenDAO.updateAttributeValue(AttributeID.TOKEN_CODE.getId(),BigInteger.valueOf(498L),"fdvdfcw12");
    }

    @Test
    public void updateServiceName(){
        tokenDAO.updateAttributeValue(AttributeID.SERVICE_NAME.getId(),BigInteger.valueOf(498L),"jira token");
    }
}
