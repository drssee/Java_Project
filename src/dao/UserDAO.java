package dao;

import domain.User;

public interface UserDAO {
    User selectOne(String id) throws Exception;
    Integer insertUser(User user) throws Exception;
    Integer updateUser(User user) throws Exception;

}
