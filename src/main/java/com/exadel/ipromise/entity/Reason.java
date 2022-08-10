package com.exadel.ipromise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reason {

    @JsonProperty("reason_id")
    private Long reasonId;

    @JsonProperty("promise_id")
    private Long promiseId;

    private String reason;

    public Reason() {
    }

    public Reason(Long reasonId, Long promiseId, String reason) {
        this.reasonId = reasonId;
        this.promiseId = promiseId;
        this.reason = reason;
    }

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }

    public Long getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(Long promiseId) {
        this.promiseId = promiseId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
