package org.cdoremus.myshowcase.service;

import org.cdoremus.myshowcase.model.User;
import org.cdoremus.myshowcase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findUserByLoginId(String loginId) {
        User found = repository.findByLoginId(loginId);
        return found;
    }

    public User findUserById(long userId) {
        User found = repository.findOne(userId);
        return found;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        return repository.save(user);
    }


}
