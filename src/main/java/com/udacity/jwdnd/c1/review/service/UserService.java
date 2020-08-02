package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.UserMapper;
import com.udacity.jwdnd.c1.review.model.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private final HashService hashService;
    private final UserMapper userMapper;

    public UserService(HashService hashService, UserMapper userMapper) {
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    public Boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(),
                user.getLastName()));
    }
}
