package com.ex.ers.tests;

import com.ex.ers.daos.RequestItemDAOImpl;
import com.ex.ers.daos.RoleDAOImpl;
import com.ex.ers.daos.UserDAOImpl;
import com.ex.ers.models.RequestItem;
import com.ex.ers.models.Role;
import com.ex.ers.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RoleDAOTests {
    private Role addRole = new Role();
    private Role updateRole = new Role();
    private Role deleteRole = new Role();

    @Before
    public void setup(){
        addRole.setDescription("TEST ROLE DO NOT DELETE");
        addRole.setName("ADD ROLE");

        updateRole.setDescription("TEST ROLE DO NOT DELETE");
        updateRole.setName("UPDATE ROLE");

        deleteRole.setDescription("TEST ROLE DO NOT DELETE");
        deleteRole.setName("DELETE ROLE");
    }

    @After
    public void tearDown(){
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        if(roleDAO.getByName(deleteRole.getName())==null){
            System.out.println("Creating Test Delete Role!");
            roleDAO.create(deleteRole);
        }
        if(roleDAO.getByName(updateRole.getName())==null){
            System.out.println("Creating Test Update Role!");
            roleDAO.create(updateRole);
        }
        Role add = roleDAO.getByName(addRole.getName());
        if(add!=null){
            System.out.println("Deleteing Add Item!");
            roleDAO.delete(add.getRoleID());
        }
    }

    @Test
    public void shouldAddNewRole(){
        RoleDAOImpl dao = new RoleDAOImpl();
        Assert.assertTrue(dao.create(addRole));
    }

    @Test
    public void shouldGetAllRoles(){
        RoleDAOImpl dao = new RoleDAOImpl();
        List<Role> roles = dao.getAll();
        Assert.assertNotNull(roles);
    }

    @Test
    public void shouldEditARole(){
        RoleDAOImpl dao = new RoleDAOImpl();
        Role edit = dao.getByName(updateRole.getName());
        edit.setDescription("CHANGED");
        Assert.assertTrue(dao.update(edit));
    }

    @Test
    public void deleteARole(){
        RoleDAOImpl dao = new RoleDAOImpl();
        Role delete = dao.getByName(deleteRole.getName());
        Assert.assertTrue(dao.delete(delete.getRoleID()));
    }

    @Test
    public void getRoleByName(){
        RoleDAOImpl dao = new RoleDAOImpl();
        Assert.assertNotNull(dao.getByName(updateRole.getName()));
    }
}
