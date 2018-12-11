package com.devstr.dao.impl;

import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.enumerations.Status;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@Transactional(rollbackFor = Exception.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractDaoTest extends AbstractDAOImpl {

    @Before
    public void setUp() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void createObjectTest() {
        String tUser = "user_test";
        String tProject = "project_test";

        BigInteger userId = createObject(ObjectType.USER.getId(), tUser);
        BigInteger projId = createObject(ObjectType.PROJECT.getId(), tProject);

        String dbUser = readObjectNameById(userId);
        String dbProj = readObjectNameById(projId);

        assertEquals(tUser, dbUser);
        assertEquals(tProject, dbProj);
        /*Cleans after test*/
        deleteObjectById(userId);
        deleteObjectById(projId);
    }

    @Test
    public void readObjectNameByIdTest() {
        String loginHn = "holinkonik";
        String loginTr = "robert_t";
        String loginRa = "alex_r";

        BigInteger idHn = readObjectIdByName(ObjectType.USER.getId(), loginHn);
        BigInteger idTr = readObjectIdByName(ObjectType.USER.getId(), loginTr);
        BigInteger idRa = readObjectIdByName(ObjectType.USER.getId(), loginRa);
        String dbHn = readObjectNameById(idHn);
        String dbTr = readObjectNameById(idTr);
        String dbRa = readObjectNameById(idRa);

        assertEquals(loginHn, dbHn);
        assertEquals(loginTr, dbTr);
        assertEquals(loginRa, dbRa);
    }

    @Test
    public void deleteObjectByIdTest() {
        String tUser = "user_test";

        BigInteger userId = createObject(ObjectType.USER.getId(), tUser);

        deleteObjectById(userId);
        String delUser = readObjectNameById(userId);

        assertNull(delUser);
    }

    @Test
    public void createAndReadAttributesTest() {
        String tProject = "project_test";
        String gitLogin = "gr2devstr";
        String gitPassword = "devstr623824!!";
        /*
        TODO: issue with date format
        Date current = new Date();
        */

        BigInteger projId = createObject(ObjectType.PROJECT.getId(), tProject);
        createAttributeValue(AttributeID.GIT_LOGIN.getId(), projId, gitLogin);
        createAttributeValue(AttributeID.GIT_PASSWORD.getId(), projId, gitPassword);
        //createAttributeDateValue(AttributeID.CREATION_DATE.getId(), projId, current);
        createAttributeListValue(AttributeID.STATUS.getId(), projId, Status.ACTIVE.getId());

        String dbProject = readObjectNameById(projId);
        String dbGitLogin = readAttributeValue(AttributeID.GIT_LOGIN.getId(), projId);
        String dbGitPassword = readAttributeValue(AttributeID.GIT_PASSWORD.getId(), projId);
        //Date dbDate = readAttributeDateValue(AttributeID.CREATION_DATE.getId(), projId);
        String dbStatus = readAttributeListValue(AttributeID.STATUS.getId(), projId);

        assertEquals(tProject, dbProject);
        assertEquals(gitLogin, dbGitLogin);
        assertEquals(gitPassword, dbGitPassword);
        //assertEquals(current, dbDate);
        assertEquals(Status.ACTIVE, Status.valueOf(dbStatus));
        /*Cleans after test*/
        deleteObjectById(projId);
    }

    @Test
    public void updateAttributesTest() {
        String tProject = "project_gg";
        String gitLogin = "gr2devstr";
        String gitPassword = "devstr623824!!";
        String gitNewLogin = "gr3airport";
        String gitNewPass = "sucks##";

        BigInteger projId = createObject(ObjectType.USER.getId(), tProject);
        createAttributeValue(AttributeID.GIT_LOGIN.getId(), projId, gitLogin);
        createAttributeValue(AttributeID.GIT_PASSWORD.getId(), projId, gitPassword);
        updateAttributeValue(AttributeID.GIT_LOGIN.getId(), projId, gitNewLogin);
        updateAttributeValue(AttributeID.GIT_PASSWORD.getId(), projId, gitNewPass);

        String dbProject = readObjectNameById(projId);
        String dbGitLogin = readAttributeValue(AttributeID.GIT_LOGIN.getId(), projId);
        String dbGitPassword = readAttributeValue(AttributeID.GIT_PASSWORD.getId(), projId);

        assertEquals(tProject, dbProject);
        assertEquals(gitNewLogin, dbGitLogin);
        assertEquals(gitNewPass, dbGitPassword);
        /*Cleans after test*/
        deleteObjectById(projId);
    }

    @Test
    public void getReferencesByObjectId() {
        BigInteger userId = readObjectIdByName(ObjectType.USER.getId(), "holinkonik");
        Collection<BigInteger> reviews = readObjectReferences(AttributeID.REVIEWS.getId(), userId);

        BigInteger reviewId1 = BigInteger.valueOf(71L);

        List<BigInteger> dbReviews = new ArrayList<>(reviews);
        Collections.sort(dbReviews);

        assertEquals(reviewId1, dbReviews.get(0));
    }

}
