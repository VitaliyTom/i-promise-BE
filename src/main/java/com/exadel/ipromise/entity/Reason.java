package com.exadel.ipromise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reason {

    @JsonProperty("reason_id")
    private int reasonId;

    @JsonProperty("promise_id")
    private int promiseId;

    @JsonProperty("reason")
    private String reason;

    public Reason() {
    }

    public Reason(int reasonId, int promiseId, String reason) {
        this.reasonId = reasonId;
        this.promiseId = promiseId;
        this.reason = reason;
    }

    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public int getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(int promiseId) {
        this.promiseId = promiseId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
