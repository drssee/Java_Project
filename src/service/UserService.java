package service;

import dto.User;

public interface UserService {
    public User selectOne(String id) throws Exception;
}
