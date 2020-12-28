package com.ex.ers.daos;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.RequestHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.Collections;
import java.util.List;

/**
 * Data access object implementation for request history
 */
public class RequestHistoryDAOImpl extends DAO implements RequestHistoryDAO {
    private static final Logger logger = LogManager.getLogger(RequestHistoryDAOImpl.class);

    /**
     * Method to retrieve history by ID
     * @param requestHistoryID the id of the request history
     * @return the request history
     */
    @Override
    public RequestHistory getByID(int requestHistoryID) {
        Session session = null;
        RequestHistory history = null;
        try{
            session = this.sessionFactory.openSession();
            history = (RequestHistory) session.get(RequestHistory.class,requestHistoryID);
            Hibernate.initialize(history.getReimbursementRequest());
            Hibernate.initialize(history.getUser());
            logger.debug("Request at ID "+requestHistoryID+" found!");
        }
        finally {
            session.close();
        }
        return history;
    }

    /**
     * Get all history by request
     * @param requestID the id of the request
     * @return a List of histories
     */
    @Override
    public List<RequestHistory> getAllByRequest(int requestID) {
        Session session = null;
        List<RequestHistory> histories = Collections.EMPTY_LIST;
        try{
            session = this.sessionFactory.openSession();
            String sql = "Select * from request_histories where request_id = :requestID";
            SQLQuery query = session.createSQLQuery(sql);
            query.setInteger("requestID",requestID);

            histories = query.list();
            logger.debug("Retrieving all history for request at id "+requestID);
        }
        finally {
            session.close();
        }
        return histories;
    }

    /**
     * Create a new request history
     * @param requestHistory the history to be created
     * @return true if success or false
     */
    @Override
    public boolean create(RequestHistory requestHistory) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(requestHistory);
            tx.commit();
            logger.debug("History Created");
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Updates a history
     * @param requestHistory the history object to update
     * @return true if success, false if not
     */
    @Override
    public boolean update(RequestHistory requestHistory) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            RequestHistory oldHistory = (RequestHistory) session.get(RequestHistory.class,requestHistory.getHistoryID());
            oldHistory.setStatus(requestHistory.getStatus());
            oldHistory.setDescription(requestHistory.getDescription());
            session.update(oldHistory);
            tx.commit();
            logger.debug("History Updated");
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Delete the history by its id
     * @param requestHistoryID the id of the request history
     * @return true if success or false
     */
    @Override
    public boolean delete(int requestHistoryID) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            RequestHistory history = (RequestHistory) session.load(RequestHistory.class,requestHistoryID);
            if(history!=null){
                session.delete(history);
                tx.commit();
                logger.debug("History Deleted");
                return true;
            }
            return false;
        }
        finally {
            session.close();
        }
    }

    /**
     * Get the history by its description
     * @param description the description of the history
     * @return the history
     */
    public RequestHistory getByDescription(String description){
        Session session = null;
        RequestHistory history = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from RequestHistory where description = :description";
            Query query = session.createQuery(hql);
            query.setString("description",description);
            List<RequestHistory> result = query.list();
            for(RequestHistory h:result){
                history = h;
            }
        }
        finally {
            session.close();
        }
        return history;
    }
}
