package dao;

import dto.Movie;
import dto.PageRequest;
import dto.Reservation;
import dto.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserDAO {
    User selectOne(String id) throws Exception;

    Integer insertUser(User user) throws Exception;

    List<Movie> selectAll_byDate(PageRequest pageRequest) throws Exception;
    List<Movie> SearchList_byDate(PageRequest pageRequest,String keyword) throws Exception;

    Integer getTotalCount() throws Exception;
    Integer getSearchedTotalCount(String keyword) throws Exception;

    List<Reservation> selectAll_reservation(String title,Timestamp schedule) throws Exception;

    Integer getReservationCount(String title,Timestamp schedule) throws Exception;
}
