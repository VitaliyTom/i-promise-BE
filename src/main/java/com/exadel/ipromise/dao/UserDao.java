package com.exadel.ipromise.dao;

import com.exadel.ipromise.entity.User;

public interface UserDao {

    void create(User user);

    User getById(int id);

    User getByEmail(String email);

    Boolean checkIfUserExistsByEmail(String email);

}
