package com.exadel.ipromise.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReasonDto {

    private Long reasonId;

    @NotNull
    private Long PromiseId;

    @NotNull(message = "cannot be empty. ")
    @Size(min = 5, max = 50, message = "must be at least 5 characters and maximum 50. ")
    private String reason;

    public ReasonDto() {
    }

    public ReasonDto(Long reasonId, Long promiseId, String reason) {
        this.reasonId = reasonId;
        PromiseId = promiseId;
        this.reason = reason;
    }

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }

    public Long getPromiseId() {
        return PromiseId;
    }

    public void setPromiseId(Long promiseId) {
        PromiseId = promiseId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
