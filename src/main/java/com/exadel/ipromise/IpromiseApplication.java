package com.exadel.ipromise;

import com.exadel.ipromise.entity.User;
import com.exadel.ipromise.jdbc.UserJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class IpromiseApplication implements CommandLineRunner {

    @Autowired
    UserJdbcDao userJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(IpromiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User pol = new User(1, "Pol", "pol@gmail.com", 2);
        User tom = new User(2, "Tom", "Tom@gmail.com", 4);
        User bill = new User(3, "Bill", "Bill@gmail.com", 5);
        User anna = new User(4, "Anna", "anna@gmail.com", 5);
        User bob = new User(2, "Bob", "bob@gmail.com", 7);

        userJdbcDao.insert(pol);
        userJdbcDao.insert(tom);
        userJdbcDao.insert(bill);
        userJdbcDao.insert(anna);
        userJdbcDao.update(bob);
    }
}


