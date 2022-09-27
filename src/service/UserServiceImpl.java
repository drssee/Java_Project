package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import dto.Movie;
import dto.PageRequest;
import dto.Reservation;
import dto.User;
import util.ConnectionUtil;

import java.sql.*;
import java.util.List;

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

    @Override
    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception {
        return userDAO.selectAll_byDate(pageRequest);
    }

    @Override
    public List<Movie> getSearchedMovieList(PageRequest pageRequest, String keyword) throws Exception {
        return userDAO.SearchList_byDate(pageRequest,keyword);
    }

    @Override
    public Integer getTotalCnt() throws Exception {
        return userDAO.getTotalCount();
    }

    @Override
    public Integer getSearchedTotalCnt(String keyword) throws Exception {
        return userDAO.getSearchedTotalCount(keyword);
    }

    @Override
    public List<Reservation> getReservationList(String title,Timestamp schedule) throws Exception {
        return userDAO.selectAll_reservation(title,schedule);
    }

    @Override
    public Integer getReservationCnt(String title, Timestamp schedule) throws Exception {
        return userDAO.getReservationCount(title,schedule);
    }
}
