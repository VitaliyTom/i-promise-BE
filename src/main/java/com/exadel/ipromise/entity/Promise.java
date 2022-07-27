package com.exadel.ipromise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Promise {

    @JsonProperty("promise_id")
    private int promiseId;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("addiction_id")
    private int addictionId;

    @JsonProperty("start_date_stamp")
    private int startDateStamp;

    @JsonProperty("amountDays")
    private int amountDays;

    @JsonProperty("name_addiction")
    private String nameAddiction;

    public Promise() {
    }

    public Promise(int promiseId, int userId, int addictionId, int startDateStamp, int amountDays, String nameAddiction) {
        this.promiseId = promiseId;
        this.userId = userId;
        this.addictionId = addictionId;
        this.startDateStamp = startDateStamp;
        this.amountDays = amountDays;
        this.nameAddiction = nameAddiction;
    }

    public int getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(int promiseId) {
        this.promiseId = promiseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddictionId() {
        return addictionId;
    }

    public void setAddictionId(int addictionId) {
        this.addictionId = addictionId;
    }

    public int getStartDateStamp() {
        return startDateStamp;
    }

    public void setStartDateStamp(int startDateStamp) {
        this.startDateStamp = startDateStamp;
    }

    public int getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(int amountDays) {
        this.amountDays = amountDays;
    }

    public String getNameAddiction() {
        return nameAddiction;
    }

    public void setNameAddiction(String nameAddiction) {
        this.nameAddiction = nameAddiction;
    }

    @Override
    public String toString() {
        return "\nPromise{" +
                "promiseId=" + promiseId +
                ", userId=" + userId +
                ", addictionId=" + addictionId +
                ", startDateStamp=" + startDateStamp +
                ", amountDays=" + amountDays +
                ", nameAddiction='" + nameAddiction + '\'' +
                '}';
    }
}
