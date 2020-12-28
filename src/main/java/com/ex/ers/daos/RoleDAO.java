package com.ex.ers.daos;

import com.ex.ers.models.Role;

import java.util.List;

/**
 * Data Access Object Interface for Roles
 */
public interface RoleDAO {
    public List<Role> getAll();

    public boolean create(Role role);
    public boolean update(Role role);
    public boolean delete(int roleID);

    public Role getByName(String name);
}
