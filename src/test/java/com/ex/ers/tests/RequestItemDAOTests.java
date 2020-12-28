package com.ex.ers.tests;

import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.daos.RequestItemDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.RequestItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RequestItemDAOTests {
    private RequestItem addItem = new RequestItem();
    private RequestItem updateItem = new RequestItem();
    private RequestItem deleteItem = new RequestItem();
    private ReimbursementRequest testRequest = new ReimbursementRequest();

    @Before
    public void setup(){
        ReimbursementRequestDAOImpl requestDAO = new ReimbursementRequestDAOImpl();
        testRequest = requestDAO.getByName("TEST REQUEST ITEMS");

        addItem.setItemComments("COMMENTS");
        addItem.setItemType("TEST");
        addItem.setDescription("ADD ITEM TEST");
        addItem.setAmount(45.67);
        addItem.setFlagged(false);
        addItem.setReimbursementRequest(testRequest);

        deleteItem.setItemComments("COMMENTS");
        deleteItem.setItemType("TEST");
        deleteItem.setDescription("DELETE ITEM TEST");
        deleteItem.setAmount(45.67);
        deleteItem.setFlagged(false);
        deleteItem.setReimbursementRequest(testRequest);

        updateItem.setItemComments("COMMENTS");
        updateItem.setItemType("TEST");
        updateItem.setDescription("UPDATE ITEM TEST");
        updateItem.setAmount(45.67);
        updateItem.setFlagged(false);
        updateItem.setReimbursementRequest(testRequest);
    }

    @After
    public void tearDown(){
        RequestItemDAOImpl requestItemDAO = new RequestItemDAOImpl();
        if(requestItemDAO.getByDescription(deleteItem.getDescription())==null){
            System.out.println("Creating Test Delete Item!");
            requestItemDAO.create(deleteItem);
        }
        if(requestItemDAO.getByDescription(updateItem.getDescription())==null){
            System.out.println("Creating Test Update Item!");
            requestItemDAO.create(updateItem);
        }
        RequestItem add = requestItemDAO.getByDescription(addItem.getDescription());
        if(add!=null){
            System.out.println("Deleteing Add Item!");
            requestItemDAO.delete(add.getRequestItemID());
        }
    }

    @Test
    public void shouldCreateARequestItem(){
        RequestItemDAOImpl requestItemDAO = new RequestItemDAOImpl();
        Assert.assertTrue(requestItemDAO.create(addItem));
    }

    @Test
    public void shouldUpdateARequestItem(){
        RequestItemDAOImpl dao = new RequestItemDAOImpl();
        RequestItem edit = dao.getByDescription(updateItem.getDescription());
        edit.setItemComments("CHANGED");
        Assert.assertTrue(dao.update(edit));
    }

    @Test
    public void shouldDeleteARequestItem(){
        RequestItemDAOImpl requestItemDAO = new RequestItemDAOImpl();
        RequestItem requestItem = requestItemDAO.getByDescription(deleteItem.getDescription());
        Assert.assertTrue(requestItemDAO.delete(requestItem.getRequestItemID()));
    }

    @Test
    public void shouldGetByID(){
        RequestItemDAOImpl dao = new RequestItemDAOImpl();
        RequestItem test = dao.getByDescription(updateItem.getDescription());
        Assert.assertNotNull(dao.getByID(test.getRequestItemID()));
    }

    @Test
    public void shouldGetByRequest(){
        RequestItemDAOImpl dao = new RequestItemDAOImpl();
        List<RequestItem> test = dao.getAllByRequest(testRequest.getRequestID());
        Assert.assertNotNull(test);
    }

    @Test
    public void shouldGetByDescription(){
        RequestItemDAOImpl dao = new RequestItemDAOImpl();
        RequestItem test = dao.getByDescription(updateItem.getDescription());
        Assert.assertNotNull(test);
    }
}
