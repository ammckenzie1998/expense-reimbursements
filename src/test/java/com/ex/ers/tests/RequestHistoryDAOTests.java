package com.ex.ers.tests;

import com.ex.ers.daos.*;
import com.ex.ers.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;

import java.util.List;

public class RequestHistoryDAOTests {
    private RequestHistory addHistory = new RequestHistory();
    private RequestHistory updateHistory = new RequestHistory();
    private RequestHistory deleteHistory = new RequestHistory();
    private ReimbursementRequest testRequest = new ReimbursementRequest();
    private User user = new User();

    @Before
    public void setup(){
        ReimbursementRequestDAOImpl requestDAO = new ReimbursementRequestDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        testRequest = requestDAO.getByName("TEST REQUEST ITEMS");
        user = userDAO.getByEmail("mmcgeowan@gmail.com");

        addHistory.setStatus(RequestStatus.NEW.toString());
        addHistory.setDescription("ADD TEST");
        addHistory.setReimbursementRequest(testRequest);
        addHistory.setUser(user);

        updateHistory.setStatus(RequestStatus.NEW.toString());
        updateHistory.setDescription("UPDATE TEST");
        updateHistory.setReimbursementRequest(testRequest);
        updateHistory.setUser(user);

        deleteHistory.setStatus(RequestStatus.NEW.toString());
        deleteHistory.setDescription("DELETE TEST");
        deleteHistory.setReimbursementRequest(testRequest);
        deleteHistory.setUser(user);
    }

    @After
    public void tearDown(){
        RequestHistoryDAOImpl dao = new RequestHistoryDAOImpl();
        if(dao.getByDescription(deleteHistory.getDescription())==null){
            System.out.println("Creating Test Delete History!");
            dao.create(deleteHistory);
        }
        if(dao.getByDescription(updateHistory.getDescription())==null){
            System.out.println("Creating Test Update History!");
            dao.create(updateHistory);
        }
        RequestHistory add = dao.getByDescription(addHistory.getDescription());
        if(add!=null){
            System.out.println("Deleteing Add History!");
            dao.delete(add.getHistoryID());
        }
    }

    @Test
    public void shouldCreateHistory(){
        RequestHistoryDAOImpl requestHistoryDAO = new RequestHistoryDAOImpl();
        Assert.assertTrue(requestHistoryDAO.create(addHistory));
    }

    @Test
    public void shouldUpdateHistory(){
        RequestHistoryDAOImpl requestHistoryDAO = new RequestHistoryDAOImpl();
        RequestHistory r = requestHistoryDAO.getByDescription(updateHistory.getDescription());
        r.setStatus(RequestStatus.NEW.toString());
        Assert.assertTrue(requestHistoryDAO.update(r));
    }

    @Test
    public void shouldGetAllHistoryByRequest(){
        RequestHistoryDAO requestHistoryDAO = new RequestHistoryDAOImpl();
        List<RequestHistory> histories = requestHistoryDAO.getAllByRequest(testRequest.getRequestID());
        Assert.assertNotNull(histories);
    }

    @Test
    public void shouldGetByID(){
        RequestHistoryDAOImpl requestHistoryDAO = new RequestHistoryDAOImpl();
        RequestHistory r = requestHistoryDAO.getByDescription(updateHistory.getDescription());
        Assert.assertNotNull(requestHistoryDAO.getByID(r.getHistoryID()));
    }

    @Test
    public void shouldGetByDescription(){
        RequestHistoryDAOImpl requestHistoryDAO = new RequestHistoryDAOImpl();
        Assert.assertNotNull(requestHistoryDAO.getByDescription(updateHistory.getDescription()));
    }

    @Test
    public void shouldDelete(){
        RequestHistoryDAOImpl requestHistoryDAO = new RequestHistoryDAOImpl();
        RequestHistory r = requestHistoryDAO.getByDescription(deleteHistory.getDescription());
        Assert.assertTrue(requestHistoryDAO.delete(r.getHistoryID()));
    }

}
