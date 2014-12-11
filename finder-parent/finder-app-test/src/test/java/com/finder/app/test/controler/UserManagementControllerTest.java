package com.finder.app.test.controler;

import com.finder.app.controler.UserManagementController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 03/01/13
 * Time: 21:52
 */
public class UserManagementControllerTest {

    private UserManagementController instance = new UserManagementController();

    @Before
    public void setUp() throws Exception {
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_password_are_exact() {
        assertTrue(instance.isPasswordExact("samePassword", "samePassword"));
    }

    @Test
    public void test_password_are_not_exact() {
        assertFalse(instance.isPasswordExact("samePassword", "anotherPassword"));
    }

    @Test
    public void test_email_is_valid() {
        assertTrue(instance.isEmailValid("email@valid.com"));
    }

    @Test
    public void test_email_is_not_valid() {

        assertFalse(instance.isEmailValid("email"));
        assertFalse(instance.isEmailValid("email@"));
        assertFalse(instance.isEmailValid("email@fff"));
        assertFalse(instance.isEmailValid("email@fff."));
    }
}
