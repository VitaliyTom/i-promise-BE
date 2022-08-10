package com.exadel.ipromise.dao;

import com.exadel.ipromise.entity.User;

public interface UserDao {

    Long create(User user);

    User getById(Long id);

    Boolean checkIfUserExistsByEmail(String email);

    User getUserByEmailAndPassword(String getEmail, String getPassword);

}
