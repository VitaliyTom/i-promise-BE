package com.exadel.ipromise.converter;

import com.exadel.ipromise.dto.ReasonDto;
import com.exadel.ipromise.entity.Reason;
import org.springframework.stereotype.Component;

@Component
public class ReasonConverter {

    public Reason convertToEntity(ReasonDto reasonDto) {
        return new Reason(reasonDto.getReasonId(), reasonDto.getPromiseId(), reasonDto.getReason());
    }

    public ReasonDto convertToDto(Reason reason) {
        return new ReasonDto(reason.getReasonId(), reason.getPromiseId(), reason.getReason());
    }
}
