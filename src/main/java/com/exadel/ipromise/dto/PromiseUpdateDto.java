package com.exadel.ipromise.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PromiseUpdateDto {

    @NotNull
    @Min(1)
    private Long promiseId;

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull(message = "cannot be empty. ")
    @Min(value = 1, message = "must be at least 1 day. ")
    private Long amountDays;

    public PromiseUpdateDto() {
    }

    public PromiseUpdateDto(Long promiseId, Long userId, Long amountDays) {
        this.promiseId = promiseId;
        this.userId = userId;
        this.amountDays = amountDays;
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

    public Long getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(Long amountDays) {
        this.amountDays = amountDays;
    }
}
