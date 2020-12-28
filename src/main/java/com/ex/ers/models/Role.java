package com.ex.ers.models;

import javax.persistence.*;

/**
 * Model Object for User Roles
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    public Role(){}

    /**
     *
     * @return the id of the role
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     *
     * @param roleID the id of the role
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    /**
     *
     * @return the name of the role
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name of the role
     */
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    /**
     *
     * @return the description of the role
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description of the role
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
