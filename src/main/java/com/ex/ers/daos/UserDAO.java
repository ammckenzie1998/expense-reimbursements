package com.ex.ers.daos;

import com.ex.ers.models.User;

import java.util.List;

/**
 * Data Access Object Interface for Users
 */
public interface UserDAO {
    public List<User> getAllUsers();
    public User getByID(int userID);
    public User getByEmail(String email);

    public boolean create(User user);
    public boolean update(User user);
    public boolean delete(User user);
}
