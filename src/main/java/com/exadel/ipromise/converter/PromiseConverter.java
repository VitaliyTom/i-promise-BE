package com.exadel.ipromise.converter;

import com.exadel.ipromise.dto.PromiseDto;
import com.exadel.ipromise.dto.PromiseListDto;
import com.exadel.ipromise.dto.PromiseUpdateDto;
import com.exadel.ipromise.entity.Promise;
import com.exadel.ipromise.entity.Reason;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PromiseConverter {

    public Promise convertToEntity(PromiseDto promiseDto) {
        return new Promise(promiseDto.getPromiseId(), promiseDto.getUserId(), promiseDto.getAddictionId(), promiseDto.getStartDateStamp(), promiseDto.getAmountDays(), promiseDto.getNameAddiction());
    }

    public Promise convertToEntity(PromiseUpdateDto promiseUpdateDto) {
        return new Promise(promiseUpdateDto.getPromiseId(), promiseUpdateDto.getUserId(), promiseUpdateDto.getAmountDays());
    }

    public List<PromiseListDto> convertToListPromiseDto(List<Promise> promises) {

        Map<Long, List<Promise>> interLayer = new HashMap<>();

        for (Promise promiseEntity : promises) {

            if (interLayer.containsKey(promiseEntity.getPromiseId())) {
                List<Promise> promisesList = interLayer.get(promiseEntity.getPromiseId());
                promisesList.add(promiseEntity);
                interLayer.put(promiseEntity.getPromiseId(), promisesList);
            } else {
                interLayer.put(promiseEntity.getPromiseId(), new ArrayList<Promise>(Collections.singletonList(promiseEntity)));
            }
        }

        List<PromiseListDto> result = new ArrayList<>();

        for (Map.Entry<Long, List<Promise>> entity : interLayer.entrySet()) {
            PromiseListDto currentPromiseDto = new PromiseListDto();
            currentPromiseDto.setPromiseId(entity.getKey());
            currentPromiseDto.setUserId(entity.getValue().get(0).getUserId());
            currentPromiseDto.setAddictionId(entity.getValue().get(0).getAddictionId());
            currentPromiseDto.setStartDateStamp(entity.getValue().get(0).getStartDateStamp());
            currentPromiseDto.setAmountDays(entity.getValue().get(0).getAmountDays());
            currentPromiseDto.setNameAddiction(entity.getValue().get(0).getNameAddiction());
            currentPromiseDto.setReason(getReasons(entity.getValue()));
            result.add(currentPromiseDto);
        }
        return result;
    }

    private List<Reason> getReasons(List<Promise> value) {
        List<Reason> result = new ArrayList<>();
        for (Promise promise : value) {
            result.add(promise.getReason());
        }
        return result;
    }
}
