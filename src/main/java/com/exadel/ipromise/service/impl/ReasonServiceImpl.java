package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.ReasonConverter;
import com.exadel.ipromise.dao.ReasonDao;
import com.exadel.ipromise.dto.ReasonDto;
import com.exadel.ipromise.entity.Reason;
import com.exadel.ipromise.service.ReasonService;
import org.springframework.stereotype.Service;


@Service("ReasonService")
public class ReasonServiceImpl implements ReasonService {

    private final ReasonConverter reasonConverter;
    private final ReasonDao reasonDao;

    public ReasonServiceImpl(ReasonConverter reasonConverter, ReasonDao reasonDao) {
        this.reasonConverter = reasonConverter;
        this.reasonDao = reasonDao;
    }

    @Override
    public ReasonDto addReason(ReasonDto reasonDto) {
        Reason reason = reasonConverter.convertToEntity(reasonDto);
        Long reasonId = reasonDao.create(reason);
        Reason newReason = reasonDao.getReasonById(reasonId);
        return reasonConverter.convertToDto(newReason);
    }

    @Override
    public ReasonDto update(ReasonDto reasonDto) {
        Reason reason = reasonConverter.convertToEntity(reasonDto);
        Reason newReason = reasonDao.update(reason);
        return reasonConverter.convertToDto(newReason);
    }

    @Override
    public void delete(Long reasonId) {
        reasonDao.delete(reasonId);
    }
}
