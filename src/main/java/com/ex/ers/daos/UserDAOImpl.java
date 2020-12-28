package com.ex.ers.daos;

import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.Role;
import com.ex.ers.models.User;
import org.hibernate.*;

import java.util.Collections;
import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

public class UserDAOImpl extends DAO implements UserDAO{

    /**
     * Returns all users
     * @return lsit of users in table
     */
    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List users = Collections.EMPTY_LIST;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from User";
            Query query = session.createQuery(hql);
            users = query.list();
        }
        finally {
            session.close();
        }
        return users;
    }

    /**
     * Returns a user by user id
     * @param userID the user's id
     * @return the user
     */
    @Override
    public User getByID(int userID) {
        Session session = null;
        User user = null;
        try{
            session = this.sessionFactory.openSession();
            user = (User) session.get(User.class,userID);
            Hibernate.initialize(user);
        }
        finally {
            session.close();
        }
        return user;
    }

    /**
     * Get user by email
     * @param email the email of the user
     * @return the user
     */
    @Override
    public User getByEmail(String email) {
        Session session = null;
        User user = null;
        try{
            session = this.sessionFactory.openSession();
            String hql = "from User where email = :email";
            Query query = session.createQuery(hql);
            query.setString("email",email);

            List<User> result = query.list();
            for(User u:result){
                user = u;
            }
        }
        finally {
            session.close();
        }
        return user;
    }

    /**
     * Creates a new user and commits
     * @param user the user to be added
     * @return true if success, false if not
     */
    @Override
    public boolean create(User user) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Update a user's details
     * @param user the user
     * @return true if success, false if not
     */
    @Override
    public boolean update(User user) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            User oldUser = (User) session.load(User.class,user.getUserID());
            oldUser.setEmail(user.getEmail());
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setPhoneNumber(user.getPhoneNumber());
            oldUser.setActive(user.isActive());
            oldUser.setPicture(user.getPicture());
            oldUser.setHash(user.getHash());
            oldUser.setSalt(user.getSalt());
            session.save(oldUser);
            tx.commit();
        }
        finally {
            session.close();
        }
        return true;
    }

    /**
     * Delete the user from the table
     * @param user the user to be deleted
     * @return true if success, false if not
     */
    @Override
    public boolean delete(User user) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            User duser = (User) session.load(User.class,user.getUserID());
            if(duser!=null){
                session.delete(duser);
                tx.commit();
                return true;
            }
            return false;
        }
        finally {
            session.close();
        }
    }
}
