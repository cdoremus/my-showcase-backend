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
        return  repository.findByLoginId(loginId);
    }

    public User findUserById(long userId) {
        return repository.findOne(userId);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        return repository.save(user);
    }

}
