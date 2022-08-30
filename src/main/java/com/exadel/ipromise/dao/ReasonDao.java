package com.exadel.ipromise.dao;

import com.exadel.ipromise.entity.Reason;

public interface ReasonDao {

    Long create(Reason reason);

    Reason getReasonById(Long reasonId);

    Reason update(Reason reason);

    int delete(Long reasonId);

}
