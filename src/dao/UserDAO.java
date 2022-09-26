package dao;

import dto.User;

public interface UserDAO {
    User selectOne(String id) throws Exception;

    Integer insertUser(User user) throws Exception;
}
