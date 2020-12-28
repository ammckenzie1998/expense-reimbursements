package com.ex.ers.tests;

import com.ex.ers.daos.UserDAOImpl;
import com.ex.ers.models.User;
import com.ex.ers.utilities.HashingUtil;
import com.ex.ers.utilities.PasswordPackage;
import org.apache.commons.codec.DecoderException;
import org.junit.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserDAOTests {
    private User addUser = new User();
    private User updateUser = new User();
    private User deleteUser = new User();
    private String addUserEmail = "ADD@gmail.com";
    private String deleteUserEmail = "DELETE@gmail.com";
    private String updateUserEmail = "UPDATE@gmail.com";
    private UserDAOImpl userDao = new UserDAOImpl();
    @Before
    public void setUp(){
        //to add
        addUser.setEmployeeID("4567");
        addUser.setActive(true);
        addUser.setFirstName("ADD");
        addUser.setLastName("ADD");
        addUser.setEmail(addUserEmail);
        PasswordPackage passwordPackage = null;
        try {
            passwordPackage = new HashingUtil().hashPassword("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        addUser.setHash(passwordPackage.getHash());
        addUser.setSalt(passwordPackage.getSalt());

        //to update
        updateUser.setEmployeeID("4567");
        updateUser.setActive(true);
        updateUser.setFirstName("UPDATE");
        updateUser.setLastName("UPDATE");
        updateUser.setEmail(updateUserEmail);
        PasswordPackage updatePasswordPackage = null;
        try {
            updatePasswordPackage = new HashingUtil().hashPassword("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        updateUser.setHash(updatePasswordPackage.getHash());
        updateUser.setSalt(updatePasswordPackage.getSalt());

        //to delete
        deleteUser.setEmployeeID("4567");
        deleteUser.setActive(true);
        deleteUser.setFirstName("DELETE");
        deleteUser.setLastName("DELETE");
        deleteUser.setEmail(deleteUserEmail);
        PasswordPackage delpasswordPackage = null;
        try {
            delpasswordPackage = new HashingUtil().hashPassword("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        deleteUser.setHash(delpasswordPackage.getHash());
        deleteUser.setSalt(delpasswordPackage.getSalt());
    }

    @After
    public void tearDown(){
        UserDAOImpl dao = new UserDAOImpl();
        if(dao.getByEmail(addUser.getEmail())!=null){
            dao.delete(dao.getByEmail(addUser.getEmail()));
        }
        if(dao.getByEmail(deleteUserEmail)==null){
            dao.create(deleteUser);
        }
        if(dao.getByEmail(updateUserEmail)==null){
            dao.create(updateUser);
        }
    }

    @Test
    public void shouldAddUser(){
        Assert.assertTrue(userDao.create(addUser));
    }

    @Test
    public void shouldGetUserById(){
        Assert.assertNotNull(userDao.getByID(7).toString());
    }

    @Test
    public void shouldGetUserByEmail(){
        Assert.assertNotNull(userDao.getByEmail("testuser@gmail.com"));
    }

    @Test
    public void shouldUpdateUser(){
        User user = userDao.getByEmail(updateUserEmail);
        user.setFirstName("Blah");
        Assert.assertTrue(userDao.update(user));
    }

    @Test
    public void shouldDeleteUser(){
        User delete = userDao.getByEmail(deleteUserEmail);
        Assert.assertTrue(userDao.delete(delete));
    }

    @Test
    public void getAllUsers(){
        Assert.assertNotNull(userDao.getAllUsers());
    }
}
