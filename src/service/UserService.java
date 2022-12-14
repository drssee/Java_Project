package service;

import domain.User;

public interface UserService extends Service {
    User selectOne(String id) throws Exception;
    Integer registerUser(User user) throws Exception;
    Integer updateUser(User user) throws Exception;
}
