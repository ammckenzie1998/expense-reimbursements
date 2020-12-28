package com.ex.ers.daos;

import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

/**
 * Data access object implementation for Reimbursement Requests
 */
public class ReimbursementRequestDAOImpl extends DAO implements ReimbursementRequestDAO{
    private static final Logger logger = LogManager.getLogger(ReimbursementRequestDAOImpl.class);


    /**
     * Get all reimbursementrequestsByStatus
     * @param status the status to search for
     * @return a list of requests
     */
    @Override
    public List<ReimbursementRequest> getAllByStatus(String status) {
        Session session = null;
        List<ReimbursementRequest> requests = Collections.EMPTY_LIST;
        try{
            session = this.sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ReimbursementRequest.class);
            criteria.add(eq("status",status));
            requests = criteria.list();
            logger.debug("Requests by status "+status+" found!");
        }
        finally {
            session.close();
        }
        return requests;
    }

    /**
     * Get all reimbursement requests
     * @return all Reimbursement Requests
     */
    @Override
    @Transactional
    public List<ReimbursementRequest> getAll() {
        Session session = null;
        List<ReimbursementRequest> requests = Collections.EMPTY_LIST;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from ReimbursementRequest";
            Query query = session.createQuery(hql);
            requests = query.list();
            logger.debug("Getting all requests");
        }
        finally {
            session.close();
        }
        return requests;
    }

    /**
     * Method to get all Reimbursement Requests by ID
     * @param id the id of the reimbursement request
     * @return the request
     */
    @Override
    public ReimbursementRequest getByID(int id) {
        Session session = null;
        ReimbursementRequest request = null;
        try{
            session = this.sessionFactory.openSession();
            request = (ReimbursementRequest) session.get(ReimbursementRequest.class,id);
            logger.debug("Request at id "+id+" found!");
        }
        finally {
            session.close();
        }
        return request;
    }

    /**
     * Get all Reimbursement Requests by User
     * @param userID the id of the user
     * @return list of reimbursement requests belonging to user
     */
    @Override
    public List<ReimbursementRequest> getAllByUser(int userID) {
        Session session = null;
        List<ReimbursementRequest> requests = Collections.EMPTY_LIST;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from ReimbursementRequest where user_id = :userID";
            Query query = session.createQuery(hql);
            query.setInteger("userID",userID);
            requests = query.list();
            logger.debug("Requests for user with id "+userID+" found!");
        }
        finally {
            session.close();
        }
        return requests;
    }

    /**
     * Gets a request by the request title
     * @param title the title of the request
     * @return the request
     */
    public ReimbursementRequest getByName(String title) {
        Session session = null;
        ReimbursementRequest request = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from ReimbursementRequest where title = :title";
            Query query = session.createQuery(hql);
            query.setString("title",title);
            List<ReimbursementRequest> result = query.list();
            for(ReimbursementRequest r:result){
                request = r;
            }
            logger.debug("Request with name "+title+" found!");
        }
        finally {
            session.close();
        }
        return request;
    }

    /**
     * Create a new reimbursement request
     * @param reimbursementRequest the reimbursement request to create
     * @return true if success, false if not
     */
    @Override
    public boolean create(ReimbursementRequest reimbursementRequest) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(reimbursementRequest);
            tx.commit();
            logger.debug("Request Created");
        } finally {
            session.close();
        }
        return true;
    }

    /**
     * Update an existing reimbursement request
     * @param reimbursementRequest the reimbursement request to update
     * @return true if success, false if not
     */
    @Override
    public boolean update(ReimbursementRequest reimbursementRequest) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            ReimbursementRequest oldRequest = (ReimbursementRequest) session.load(ReimbursementRequest.class,reimbursementRequest.getRequestID());
            oldRequest.setTitle(reimbursementRequest.getTitle());
            oldRequest.setDescription(reimbursementRequest.getDescription());
            oldRequest.setStatus(reimbursementRequest.getStatus());
            oldRequest.setRequestComments(reimbursementRequest.getRequestComments());
            session.save(oldRequest);
            tx.commit();
            logger.debug("Request Updated");
        } finally {
            session.close();
        }
        return true;
    }

    /**
     * Delete a reimbursement request
     * @param requestID the id of the request to delete
     * @return true if success, false if not
     */
    @Override
    public boolean delete(int requestID) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            ReimbursementRequest request = (ReimbursementRequest) session.load(ReimbursementRequest.class,requestID);
            if(request!=null){
                session.delete(request);
                tx.commit();
                logger.debug("Request Deleted");
                return true;

            }
            return false;
        }
        finally {
            session.close();
        }
    }
}
