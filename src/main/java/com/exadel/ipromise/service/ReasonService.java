package com.exadel.ipromise.service;


import com.exadel.ipromise.dto.ReasonDto;

public interface ReasonService {

    ReasonDto addReason(ReasonDto reasonDto);

    ReasonDto update(ReasonDto reasonDto);

    void delete(Long reasonId);
}
