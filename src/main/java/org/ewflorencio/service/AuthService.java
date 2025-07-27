package org.ewflorencio.service;

import org.ewflorencio.dao.UserDao;
import org.ewflorencio.model.User;

public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean authenticate(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null) return false;

        return user.getPassword().equals(password);
    }

}
