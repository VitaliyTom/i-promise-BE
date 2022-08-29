package com.exadel.ipromise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Promise {

    @JsonProperty("promise_id")
    private Long promiseId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("addiction_id")
    private Long addictionId;

    @JsonProperty("start_date_stamp")
    private Long startDateStamp;

    @JsonProperty("amount_days")
    private Long amountDays;

    @JsonProperty("name_addiction")
    private String nameAddiction;

    private Reason reason;

    public Promise() {
    }

    public Promise(Long promiseId, Long userId, Long amountDays) {
        this.promiseId = promiseId;
        this.userId = userId;
        this.amountDays = amountDays;
    }

    public Promise(Long promiseId, Long userId, Long addictionId, Long startDateStamp, Long amountDays, String nameAddiction) {
        this.promiseId = promiseId;
        this.userId = userId;
        this.addictionId = addictionId;
        this.startDateStamp = startDateStamp;
        this.amountDays = amountDays;
        this.nameAddiction = nameAddiction;
    }

    public Promise(Long promiseId, Long userId, Long addictionId, Long startDateStamp, Long amountDays, String nameAddiction, Reason reason) {
        this.promiseId = promiseId;
        this.userId = userId;
        this.addictionId = addictionId;
        this.startDateStamp = startDateStamp;
        this.amountDays = amountDays;
        this.nameAddiction = nameAddiction;
        this.reason = reason;
    }

    public Long getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(Long promiseId) {
        this.promiseId = promiseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddictionId() {
        return addictionId;
    }

    public void setAddictionId(Long addictionId) {
        this.addictionId = addictionId;
    }

    public Long getStartDateStamp() {
        return startDateStamp;
    }

    public void setStartDateStamp(Long startDateStamp) {
        this.startDateStamp = startDateStamp;
    }

    public Long getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(Long amountDays) {
        this.amountDays = amountDays;
    }

    public String getNameAddiction() {
        return nameAddiction;
    }

    public void setNameAddiction(String nameAddiction) {
        this.nameAddiction = nameAddiction;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

}
