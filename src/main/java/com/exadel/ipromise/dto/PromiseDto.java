package com.exadel.ipromise.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PromiseDto {

    private Long promiseId;

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    @Min(1)
    private Long addictionId;

    @NotNull(message = "cannot be empty. ")
    private Long startDateStamp;

    @NotNull(message = "cannot be empty. ")
    @Min(value = 1, message = "must be at least 1 day. ")
    private Long amountDays;

    private String nameAddiction;

    private String reason;

    public PromiseDto() {
    }

    public PromiseDto(Long promiseId, Long userId, Long addictionId, Long startDateStamp, Long amountDays, String nameAddiction, String reason) {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
