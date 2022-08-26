package com.exadel.ipromise.dto;

import com.exadel.ipromise.entity.Reason;

import java.util.List;

public class PromiseListDto {

    private Long promiseId;

    private Long userId;

    private Long addictionId;

    private Long startDateStamp;

    private Long amountDays;

    private String nameAddiction;

    private List<Reason> reason;

    public PromiseListDto() {
    }

    public PromiseListDto(Long promiseId, Long userId, Long addictionId, Long startDateStamp, Long amountDays, String nameAddiction, List<Reason> reason) {
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

    public List<Reason> getReason() {
        return reason;
    }

    public void setReason(List<Reason> reason) {
        this.reason = reason;
    }

}
