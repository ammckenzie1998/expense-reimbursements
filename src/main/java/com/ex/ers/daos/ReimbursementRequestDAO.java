package com.ex.ers.daos;

import com.ex.ers.models.ReimbursementRequest;

import java.util.List;

/**
 * Data Access Object Interface for Reimbursement Requests
 */
public interface ReimbursementRequestDAO {
    public List<ReimbursementRequest> getAllByStatus(String status);
    public List<ReimbursementRequest> getAll();
    public ReimbursementRequest getByID(int id);
    public List<ReimbursementRequest> getAllByUser(int userID);

    public boolean create(ReimbursementRequest reimbursementRequest);
    public boolean update(ReimbursementRequest reimbursementRequest);
    public boolean delete(int requestID);

}
