package dao;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;

import java.util.List;

public interface UserDAO {
    User selectOne(String id) throws Exception;
    Integer insertUser(User user) throws Exception;
    List<Movie> selectAll_byDate(PageRequest pageRequest) throws Exception;
    List<Movie> SearchList_byDate(PageRequest pageRequest,String keyword) throws Exception;
    Integer getTotalCount() throws Exception;
    Integer getSearchedTotalCount(String keyword) throws Exception;
    List<Reservation> selectAll_reservation(int tno) throws Exception;
    Integer getReservationCount(int tno) throws Exception;
    void reservation(Movie movie,Reservation reservation,User user);
    List<Reservation> selectAll_reservation_byUser(String id) throws Exception;
    Integer updateUser(User user) throws Exception;
}
