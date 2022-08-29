package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.PromiseConverter;
import com.exadel.ipromise.dao.PromiseDao;
import com.exadel.ipromise.dao.ReasonDao;
import com.exadel.ipromise.dto.PromiseDto;
import com.exadel.ipromise.dto.PromiseListDto;
import com.exadel.ipromise.dto.PromiseUpdateDto;
import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.Promise;
import com.exadel.ipromise.entity.Reason;
import com.exadel.ipromise.exception.UserIsInvalidAuthException;
import com.exadel.ipromise.service.PromiseService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service("PromiseService")
public class PromiseServiceImpl implements PromiseService {

    private final String USER_UNAUTHORIZED = "User unauthorized";

    private final PromiseConverter promiseConverter;
    private final PromiseDao promiseDao;
    private final ReasonDao reasonDao;

    public PromiseServiceImpl(PromiseConverter promiseConverter, PromiseDao promiseDao, ReasonDao reasonDao) {
        this.promiseConverter = promiseConverter;
        this.promiseDao = promiseDao;
        this.reasonDao = reasonDao;
    }

    @Override
    public List<PromiseListDto> addPromise(PromiseDto promiseDto) {

        Promise promise = promiseConverter.convertToEntity(promiseDto);

        Long idPromise = promiseDao.addPromise(promise);
        Reason reason = new Reason(null, idPromise, promiseDto.getReason());
        reasonDao.create(reason);
        List<Promise> promises = promiseDao.getPromisesByIdUser(promise.getUserId());

        return promiseConverter.convertToListPromiseDto(promises);
    }

    @Override
    public List<PromiseListDto> get(Long userId) {

        List<Promise> promises = promiseDao.getPromisesByIdUser(userId);

        return promiseConverter.convertToListPromiseDto(promises);
    }

    @Override
    public List<PromiseListDto> update(PromiseUpdateDto promiseUpdateDto) {

        Promise promise = promiseConverter.convertToEntity(promiseUpdateDto);
        promiseDao.update(promise);
        List<Promise> promises = promiseDao.getPromisesByIdUser(promise.getUserId());

        return promiseConverter.convertToListPromiseDto(promises);
    }

    @Override
    public List<PromiseListDto> delete( Long promiseId, HttpSession session) {
        if ( session.getAttribute("isLogged") == null || session.getAttribute("isLogged").equals(false)){
            throw new UserIsInvalidAuthException(USER_UNAUTHORIZED);
        }
        promiseDao.delete(promiseId);
        UserDto userDto = (UserDto) session.getAttribute("user");
        List<Promise> promises = promiseDao.getPromisesByIdUser(userDto.getUserId());
        return promiseConverter.convertToListPromiseDto(promises);
    }
}
