package com.ex.ers.daos;

import com.ex.ers.models.RequestHistory;

import java.util.List;

/**
 * Data Access Object Interface for Request History/Changes
 */
public interface RequestHistoryDAO {
    public RequestHistory getByID(int requestHistoryID);
    public List<RequestHistory> getAllByRequest(int requestID);

    public boolean create(RequestHistory requestHistory);
    public boolean update(RequestHistory requestHistory);
    public boolean delete(int requestHistoryID);
}
