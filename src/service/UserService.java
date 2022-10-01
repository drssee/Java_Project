package service;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;

import java.util.List;

public interface UserService {
    public User selectOne(String id) throws Exception;
    public Integer registerUser(User user) throws Exception;
    public Integer updateUser(User user) throws Exception;
    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception;
    public List<Movie> getSearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;
    public Integer getTotalCnt() throws Exception;
    public Integer getSearchedTotalCnt(String keyword) throws Exception;
    public List<Reservation> getReservationList(int tno) throws Exception;
    public Integer getReservationCnt(int tno) throws Exception;
    public void reservation(int selected,Movie movie,User user) throws Exception;
    public List<Reservation> getReservationList_byUser(String id) throws Exception;
    public void deleteRes(int rno,User user, int price) throws Exception;
}
