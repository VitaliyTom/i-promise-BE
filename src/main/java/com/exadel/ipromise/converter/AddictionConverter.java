package com.exadel.ipromise.converter;

import com.exadel.ipromise.dto.AddictionListDto;
import com.exadel.ipromise.entity.Addiction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddictionConverter {

    public List<AddictionListDto> convertToDto(List<Addiction> addictions) {

        List<AddictionListDto> addictionListDto = new ArrayList<AddictionListDto>();

        for (Addiction addiction : addictions) {
            addictionListDto.add(new AddictionListDto(addiction.getAddictionId(), addiction.getNameAddiction()));
        }

        return addictionListDto;
    }

}
