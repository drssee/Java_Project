package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import domain.User;

public class UserServiceImpl implements UserService { //서비스 인터페이스 상속

    public UserServiceImpl() {}

    UserDAO userDAO = new UserDAOImpl();
    @Override
    public User selectOne(String id) throws Exception {
        return userDAO.selectOne(id);
    }

    @Override
    public Integer registerUser(User user) throws Exception {
        return userDAO.insertUser(user);
    }

    @Override
    public Integer updateUser(User user) throws Exception {
        return userDAO.updateUser(user);
    }
}
