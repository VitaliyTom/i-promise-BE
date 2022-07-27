package com.exadel.ipromise.dao;

import com.exadel.ipromise.entity.User;

public interface UserDao {

    void create(User user);

    Boolean checkUserByEmail(User user);

    User getUserById(int id);

    User getUserByEmail(User user);

}
