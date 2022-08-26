package com.exadel.ipromise.dao;

import com.exadel.ipromise.entity.Promise;

import java.util.List;

public interface PromiseDao {

    Long addPromise(Promise promise);

    List<Promise> getPromisesByIdUser(Long userId);

    int update(Promise promise);

    int delete(Long promiseId);
}
