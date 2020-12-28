package com.ex.ers.daos;

import com.ex.ers.models.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

/**
 * Data Access object for Roles table
 */
public class RoleDAOImpl extends DAO implements RoleDAO {

    /**
     * Get all Roles
     * @return all roles
     */
    @Override
    public List<Role> getAll() {
        Session session = null;
        List<Role> roles = Collections.EMPTY_LIST;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from Role";
            Query query = session.createQuery(hql);

            roles = query.list();
        }
        finally {
            session.close();
        }
        return roles;
    }

    /**
     * Create a new role
     * @param role the role to create
     * @return true if success, false if not
     */
    @Override
    public boolean create(Role role) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(role);
            tx.commit();
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Update role name and description
     * @param role the role to update
     * @return true if successful, false if not
     */
    @Override
    public boolean update(Role role) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Role r = (Role) session.load(Role.class,role.getRoleID());
            r.setName(role.getName());
            r.setDescription(role.getDescription());
            session.save(r);
            tx.commit();
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Delete a role
     * @param roleID the id of the role to be delete
     * @return true if success, false if not
     */
    @Override
    public boolean delete(int roleID) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Role role = (Role) session.load(Role.class,roleID);
            if(role!=null){
                session.delete(role);
                tx.commit();
                return true;
            }
            return false;
        }
        finally {
            session.close();
        }
    }

    /**
     * Get a role by name
     * @param name the name of the role
     * @return the role
     */
    @Override
    public Role getByName(String name) {
        Session session = null;
        Role role = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from Role where name = :name";
            Query query = session.createQuery(hql);
            query.setString("name",name.toUpperCase());

            List<Role> result = query.list();
            for(Role r:result){
                role = r;
            }
        }
        finally {
            session.close();
        }
        return role;
    }
}
