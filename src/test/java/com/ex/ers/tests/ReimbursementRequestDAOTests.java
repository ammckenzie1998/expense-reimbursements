package com.ex.ers.tests;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.daos.UserDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.RequestStatus;
import com.ex.ers.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReimbursementRequestDAOTests {
    private ReimbursementRequest addRequest = new ReimbursementRequest();
    private ReimbursementRequest updateRequest = new ReimbursementRequest();
    private ReimbursementRequest deleteRequest = new ReimbursementRequest();

    @Before
    public void setUp(){
        //addRequest
        UserDAOImpl userDAO = new UserDAOImpl();
        User u = userDAO.getByEmail("mmcgeowan@gmail.com");
        addRequest.setUser(u);
        addRequest.setTitle("ADDED REQUEST");
        addRequest.setStatus(RequestStatus.NEW.toString());
        addRequest.setDescription("A test request");
        addRequest.setRequestComments("Comments");

        //updateRequest
        updateRequest.setUser(u);
        updateRequest.setTitle("UPDATE REQUEST");
        updateRequest.setStatus(RequestStatus.NEW.toString());
        updateRequest.setDescription("A test request");
        updateRequest.setRequestComments("Comments");

        //deleteRequest
        deleteRequest.setUser(u);
        deleteRequest.setTitle("DELETE REQUEST");
        deleteRequest.setStatus(RequestStatus.NEW.toString());
        deleteRequest.setDescription("A test request");
        deleteRequest.setRequestComments("Comments");
    }

    @After
    public void tearDown(){
        ReimbursementRequestDAOImpl dao = new ReimbursementRequestDAOImpl();
        if(dao.getByName(deleteRequest.getTitle())==null){
            System.out.println("Creating Test Delete Request!");
            dao.create(deleteRequest);
        }
        if(dao.getByName(updateRequest.getTitle())==null){
            System.out.println("Creating Test Update Request!");
            dao.create(updateRequest);
        }
        ReimbursementRequest add = dao.getByName(addRequest.getTitle());
        if(add!=null){
            System.out.println("Deleteing Add Request!");
            dao.delete(add.getRequestID());
        }
    }

    @Test
    public void shouldCreateAReimbursementRequest(){
        ReimbursementRequestDAO dao = new ReimbursementRequestDAOImpl();
        Assert.assertTrue(dao.create(addRequest));
    }

    @Test
    public void shouldEditAReimbursementRequest(){
        ReimbursementRequestDAOImpl dao = new ReimbursementRequestDAOImpl();
        ReimbursementRequest request = dao.getByName(updateRequest.getTitle());
        request.setDescription("Changed!");
        Assert.assertTrue(dao.update(request));
    }

    @Test
    public void shouldGetAllRequests(){
        ReimbursementRequestDAOImpl dao = new ReimbursementRequestDAOImpl();
        Assert.assertNotNull(dao.getAll());
    }

    @Test
    public void shouldGetAllByStatus(){
        ReimbursementRequestDAOImpl dao = new ReimbursementRequestDAOImpl();
        Assert.assertNotNull(dao.getAllByStatus(RequestStatus.NEW.toString()));
    }

    @Test
    public void shouldDeleteRequest(){
        ReimbursementRequestDAOImpl dao = new ReimbursementRequestDAOImpl();
        ReimbursementRequest request = dao.getByName(deleteRequest.getTitle());
        Assert.assertTrue(dao.delete(request.getRequestID()));
    }
}
