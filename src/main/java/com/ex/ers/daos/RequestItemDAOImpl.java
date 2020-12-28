package com.ex.ers.daos;

import com.ex.ers.models.RequestItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

/**
 * Data access object implementation for request items
 */
public class RequestItemDAOImpl extends DAO implements RequestItemDAO {
    private static final Logger logger = LogManager.getLogger(RequestItemDAOImpl.class);

    /**
     * Method to retrieve request item by ID
     * @param itemID the id of the item
     * @return RequestItem
     */
    @Override
    public RequestItem getByID(int itemID) {
        Session session = null;
        RequestItem item = null;
        try{
            session = this.sessionFactory.openSession();
            item = (RequestItem) session.get(RequestItem.class,itemID);
            logger.debug("Request item at id "+itemID+" retrieved");
        }
        finally {
            session.close();
        }
        return item;
    }

    /**
     * Method to return a list of request items
     * @param requestID the id of the request
     * @return a list of request items
     */
    @Override
    public List<RequestItem> getAllByRequest(int requestID) {
        Session session = null;
        List<RequestItem> requests = Collections.EMPTY_LIST;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from RequestItem where request_id = :requestID";
            Query query = session.createQuery(hql);
            query.setInteger("requestID",requestID);
            requests = query.list();
            logger.debug("Retrieving all at request id "+requestID);
        }
        finally {
            session.close();
        }
        return requests;
    }

    /**
     * Create a new request item
     * @param requestItem the item to be created
     * @return true if success or false
     */
    @Override
    public boolean create(RequestItem requestItem) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(requestItem);
            tx.commit();
            logger.debug("New Request Item Created");
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Update a request item
     * @param requestItem the request item to update
     * @return true if success, false
     */
    @Override
    public boolean update(RequestItem requestItem) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            RequestItem oldRequest = (RequestItem) session.get(RequestItem.class,requestItem.getRequestItemID());
            oldRequest.setReceipt(requestItem.getReceipt());
            oldRequest.setItemType(requestItem.getItemType());
            oldRequest.setItemComments(requestItem.getItemComments());
            oldRequest.setFlagged(requestItem.isFlagged());
            oldRequest.setFlaggedComments(requestItem.getFlaggedComments());
            oldRequest.setDescription(requestItem.getDescription());
            oldRequest.setAmount(requestItem.getAmount());
            session.save(oldRequest);
            tx.commit();
            logger.debug("Request Item Updated");
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Delete a request item by id
     * @param itemID the id of the request item
     * @return true if success or false
     */
    @Override
    public boolean delete(int itemID) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            RequestItem item = (RequestItem) session.load(RequestItem.class,itemID);
            if(item!=null){
                session.delete(item);
                tx.commit();
                logger.debug("Request Item Deleted");
                return true;
            }
            return false;
        }
        finally {
            session.close();
        }
    }

    /**
     * Get request items by description
     * @param description the description of the request item
     * @return the request item
     */
    public RequestItem getByDescription(String description){
        Session session = null;
        RequestItem item = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from RequestItem where description = :description";
            Query query = session.createQuery(hql);
            query.setString("description",description);
            List<RequestItem> result = query.list();
            for(RequestItem i:result){
                item = i;
            }
        }
        finally {
            session.close();
        }
        return item;
    }
}
