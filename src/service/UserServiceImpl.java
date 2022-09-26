package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import dto.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserServiceImpl implements UserService { //서비스 인터페이스 상속
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public User selectOne(String id) throws Exception {
        return userDAO.selectOne(id);
    }

    @Override
    public Integer registerUser(User user) throws Exception {
        return userDAO.insertUser(user);
    }
}
