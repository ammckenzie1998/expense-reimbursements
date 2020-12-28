package com.ex.ers.daos;

import com.ex.ers.models.RequestItem;

import java.util.List;

/**
 * Data Access Object Interface for Request Items
 */
public interface RequestItemDAO {
    public RequestItem getByID(int itemID);
    public List<RequestItem> getAllByRequest(int requestID);

    public boolean create(RequestItem requestItem);
    public boolean update(RequestItem requestItem);
    public boolean delete(int itemID);
}
