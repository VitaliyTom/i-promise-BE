package com.exadel.ipromise.dao;

import com.exadel.ipromise.entity.Promise;

import java.util.List;

public interface PromiseDao {

    Long create(Promise promise);

    List<Promise> getByIdUser(Long userId);
}
