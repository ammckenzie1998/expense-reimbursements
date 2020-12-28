package com.ex.ers.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

/**
 * Model Object for Request Items
 */
@Entity
@Table(name = "request_items")
public class RequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_item_id")
    private int requestItemID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private ReimbursementRequest reimbursementRequest;

    @Column(name="item_type",nullable = false)
    private String itemType;

    @Column(name = "date_submitted")
    @CreationTimestamp
    private java.sql.Date dateSubmitted;

    @Column(name = "description")
    private String description;

    @Column(name = "amount",nullable = false)
    private double amount;

    @Column(name = "item_comments")
    private String itemComments;

    @Column(name = "receipt")
    private byte[] receipt;

    @Column(name = "is_flagged",nullable = false)
    private boolean isFlagged;

    @Column(name = "flagged_comments")
    private String flaggedComments;

    public RequestItem(){}

    /**
     *
     * @return the item id
     */
    public int getRequestItemID() {
        return requestItemID;
    }

    /**
     *
     * @param requestItemID the item id
     */
    public void setRequestItemID(int requestItemID) {
        this.requestItemID = requestItemID;
    }

    /**
     *
     * @return the associated request
     */
    public ReimbursementRequest getReimbursementRequest() {
        return reimbursementRequest;
    }

    /**
     *
     * @param reimbursementRequest the associated request
     */
    public void setReimbursementRequest(ReimbursementRequest reimbursementRequest) {
        this.reimbursementRequest = reimbursementRequest;
    }

    /**
     *
     * @return the item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     *
     * @param itemType the item type
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     *
     * @return the date submitted
     */
    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     *
     * @param dateSubmitted the date submitted
     */
    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    /**
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount the amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return comments on this item
     */
    public String getItemComments() {
        return itemComments;
    }

    /**
     *
     * @param itemComments comments on this item
     */
    public void setItemComments(String itemComments) {
        this.itemComments = itemComments;
    }

    /**
     *
     * @return the receipt (optional)
     */
    public byte[] getReceipt() {
        return receipt;
    }

    /**
     *
     * @param receipt the receipt
     */
    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    /**
     *
     * @return whether or not this item is flagged for review
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     *
     * @param flagged whether or not this item is flagged for review
     */
    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    /**
     *
     * @return comments regarding the flag
     */
    public String getFlaggedComments() {
        return flaggedComments;
    }

    /**
     *
     * @param flaggedComments comments regarding the flag
     */
    public void setFlaggedComments(String flaggedComments) {
        this.flaggedComments = flaggedComments;
    }

    @Override
    public String toString() {
        return "RequestItem{" +
                "request_item_id=" + requestItemID +
                ", reimbursementRequest=" + reimbursementRequest +
                ", itemType='" + itemType + '\'' +
                ", dateSubmitted=" + dateSubmitted +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", itemComments='" + itemComments + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", isFlagged=" + isFlagged +
                ", flaggedComments='" + flaggedComments + '\'' +
                '}';
    }
}
