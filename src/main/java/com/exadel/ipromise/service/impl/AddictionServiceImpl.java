package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.AddictionConverter;
import com.exadel.ipromise.dao.AddictionDao;
import com.exadel.ipromise.dto.AddictionListDto;
import com.exadel.ipromise.entity.Addiction;
import com.exadel.ipromise.service.AddictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AddictionService")
public class AddictionServiceImpl implements AddictionService {

    private final AddictionDao addictionDao;
    private final AddictionConverter addictionConverter;

    public AddictionServiceImpl(AddictionDao addictionDao, AddictionConverter addictionConverter) {
        this.addictionDao = addictionDao;
        this.addictionConverter = addictionConverter;
    }


    @Override
    public List<AddictionListDto> getAddictions() {
        List<Addiction> addictions = addictionDao.getAddictions();
        return addictionConverter.convertToDto(addictions);
    }
}
