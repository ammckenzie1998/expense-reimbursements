package com.ex.ers.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

/**
 * Model Object for Users
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name="email",unique = true,nullable = false)
    private String email;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="hash",nullable = false)
    private String hash;

    @Column(name="salt",nullable = false)
    private String salt;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="company_employee_id")
    private String employeeID;

    @Column(name="picture")
    private byte[] picture;

    @Column(name="is_active",nullable = false)
    private boolean isActive;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    )
    private Set<Role> roles;

    public User(){}

    /**
     *
     * @return the id of the user
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID the id of the user
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName the last nameof the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return the user's hash
     */
    public String getHash() {
        return hash;
    }

    /**
     *
     * @param hash the user's hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     *
     * @return the phone number of the user
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber the phone number of the user
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return the employee id
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     *
     * @param employeeID the employee id
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     *
     * @return a picture of the user
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     *
     * @param picture a picture of the user
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     *
     * @return whether or not the user is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     *
     * @param active whether or not the user is active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the salt of the user
     */
    public String getSalt() {
        return salt;
    }

    /**
     *
     * @param salt the salt of the user
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *
     * @return roles of this user
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles roles of this user
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", picture=" + Arrays.toString(picture) +
                ", isActive=" + isActive +
                '}';
    }
}
