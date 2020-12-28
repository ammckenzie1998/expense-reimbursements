package com.ex.ers.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Model Object for Reimbursement Requests
 */
@Entity
@Table(name = "reimbursement_requests")
public class ReimbursementRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "request_comments")
    private String requestComments;

    @Column(name = "date_submitted")
    @CreationTimestamp
    private java.sql.Date dateSubmitted;

    @Column(name = "status",nullable = false)
    private String status;

    public ReimbursementRequest(){}

    /**
     *
     * @return the request id
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     *
     * @param requestID the id to set
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    /**
     *
     * @return the user on this request
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user the user to set on this request
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return the title of this request
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the title to set on this request
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the description of this request
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return comments on this request
     */
    public String getRequestComments() {
        return requestComments;
    }

    /**
     *
     * @param requestComments comments on this request
     */
    public void setRequestComments(String requestComments) {
        this.requestComments = requestComments;
    }

    /**
     *
     * @return the date this request was added
     */
    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     *
     * @param dateSubmitted the date this request was added
     */
    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    /**
     *
     * @return the status of this request
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status the status of the request
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReimbursementRequest{" +
                "requestID=" + requestID +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", requestComments='" + requestComments + '\'' +
                ", dateSubmitted=" + dateSubmitted +
                ", status='" + status + '\'' +
                '}';
    }
}
