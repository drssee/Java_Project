package service;

import dto.Movie;
import dto.PageRequest;
import dto.Reservation;
import dto.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    public User selectOne(String id) throws Exception;

    public Integer registerUser(User user) throws Exception;

    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception;
    public List<Movie> getSearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;

    public Integer getTotalCnt() throws Exception;

    public Integer getSearchedTotalCnt(String keyword) throws Exception;

    public List<Reservation> getReservationList(String title,Timestamp schedule) throws Exception;

    public Integer getReservationCnt(String title,Timestamp schedule) throws Exception;
}
