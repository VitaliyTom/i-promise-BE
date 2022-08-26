package com.exadel.ipromise.service;

import com.exadel.ipromise.dto.PromiseDto;
import com.exadel.ipromise.dto.PromiseListDto;
import com.exadel.ipromise.dto.PromiseUpdateDto;

import java.util.List;

public interface PromiseService {

    List<PromiseListDto> addPromise(PromiseDto promiseDto);

    List<PromiseListDto> get(Long userId);

    List<PromiseListDto> update(PromiseUpdateDto promiseUpdateDto);

    List<PromiseListDto> delete(PromiseUpdateDto promiseUpdateDto);

}
