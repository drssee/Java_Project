package service;

import domain.User;

public interface UserService {
    public User selectOne(String id) throws Exception;
    public Integer registerUser(User user) throws Exception;
    public Integer updateUser(User user) throws Exception;
}
