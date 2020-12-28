package com.ex.ers.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

/**
 * Model object for Request History
 */
@Entity
@Table(name = "request_histories")
public class RequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_history_id")
    private int historyID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private ReimbursementRequest reimbursementRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "date_changed")
    @CreationTimestamp
    private java.sql.Date dateChanged;

    @Column(name="status",nullable = false)
    private String status;

    @Column(name = "description")
    private String description;

    public RequestHistory(){}

    /**
     *
     * @return the id of the history
     */
    public int getHistoryID() {
        return historyID;
    }

    /**
     *
     * @param historyID the id of the history
     */
    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    /**
     *
     * @return the request associated
     */
    public ReimbursementRequest getReimbursementRequest() {
        return reimbursementRequest;
    }

    /**
     *
     * @param reimbursementRequest the request associated
     */
    public void setReimbursementRequest(ReimbursementRequest reimbursementRequest) {
        this.reimbursementRequest = reimbursementRequest;
    }

    /**
     *
     * @return the user who created
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user the user who created
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return the date this history was added
     */
    public Date getDateChanged() {
        return dateChanged;
    }

    /**
     *
     * @param dateChanged the date this history was added
     */
    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    /**
     *
     * @return the status of this history
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status the status of this history
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return the description of this history
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description of this history
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RequestHistory{" +
                "historyID=" + historyID +
                ", reimbursementRequest=" + reimbursementRequest.getRequestID() +
                ", userID=" + user.getUserID() +
                ", dateChanged=" + dateChanged +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
