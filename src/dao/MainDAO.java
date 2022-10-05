package dao;

import domain.Movie;
import domain.Reservation;
import domain.User;

import java.util.List;
import java.util.Map;

public interface MainDAO extends DAO {
    void deleteReservation(int rno, User user, int price) throws Exception;
    List<Reservation> selectAll_reservation(int tno) throws Exception;
    Integer getReservationCount(int tno) throws Exception;
    void reservation(Movie movie, Reservation reservation, User user) throws Exception;
    List<Reservation> selectAll_reservation_byUser(String id) throws Exception;
    Map<Integer,Integer> groupByGender(List<User> userList,int tno) throws Exception;
    Map<Integer,Integer> groupByAge(List<User> userList,int tno) throws Exception;
}
