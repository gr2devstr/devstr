package com.devstr.dao.impl;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;
import com.devstr.model.impl.UserImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.*;

@Ignore
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    private User holinkonik;
    private User roberttalab;
    private User ksushamir;

    private String password;

    @Autowired
    UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        Locale.setDefault(Locale.ENGLISH);

        password = "$2a$10$cv05WJk5Wd5WkPzjFBXwCOP4yVYn72IYyeQ5GCcJhRs1o6CsjKyTW";

        holinkonik = new UserImpl.Builder(
                "holinkonik",
                password,
                "holinkonik@gmail.com",
                null,
                "Mykola",
                "Holinko",
                UserRole.DEVELOPER,
                Status.ACTIVE)
                .setUserId(BigInteger.valueOf(62L))
                .build();

        roberttalab = new UserImpl.Builder(
                "robert_t",
                password,
                "rtalabishka@gmail.com",
                null,
                "Robert",
                "Talabishka",
                UserRole.DEVELOPER,
                Status.ACTIVE)
                .setUserId(BigInteger.valueOf(63L))
                .build();

        ksushamir = new UserImpl.Builder(
                "ksusha_m",
                password,
                "coldlandd@gmail.com",
                null,
                "Ksenia",
                "Miroshnichenko",
                UserRole.PROJECT_MANAGER,
                Status.ACTIVE)
                .setUserId(BigInteger.valueOf(64L))
                .build();
    }

    @Test
    public void readUserByLoginTest() {
        User testNik = userDAO.readBasicUserByLogin("holinkonik");
        User testRob = userDAO.readBasicUserByLogin("robert_t");
        User testKsu = userDAO.readBasicUserByLogin("ksusha_m");

        assertBasicUsers(holinkonik, testNik);
        assertBasicUsers(roberttalab, testRob);
        assertBasicUsers(ksushamir, testKsu);
    }

    @Test(expected = NullPointerException.class)
    public void readUserByFalseLoginTest() {
        User unExistUser = userDAO.readBasicUserByLogin("bad_login");
    }

    @Test
    public void readAllUsersTest() {
        Collection<User> users = userDAO.readAllUsers();

        assertTrue(users.contains(holinkonik));
        assertTrue(users.contains(roberttalab));
        assertTrue(users.contains(ksushamir));
    }

    private void assertBasicUsers(User one, User two) {
        assertEquals(one.getUserId(), two.getUserId());
        assertEquals(one.getLogin(), two.getLogin());
        assertEquals(one.getPassword(), two.getPassword());
        assertEquals(one.getEmail(), two.getEmail());
        assertEquals(one.getFirstName(), two.getFirstName());
        assertEquals(one.getLastName(), two.getLastName());
        assertEquals(one.getRole(), two.getRole());
        assertEquals(one.getStatus(), two.getStatus());
    }


}
