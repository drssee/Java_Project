package dao;

import domain.Movie;
import domain.Reservation;
import domain.User;

import java.util.List;

public interface MainDAO {
    void deleteReservation(int rno, User user, int price) throws Exception;
    List<Reservation> selectAll_reservation(int tno) throws Exception;
    Integer getReservationCount(int tno) throws Exception;
    void reservation(Movie movie, Reservation reservation, User user) throws Exception;
    List<Reservation> selectAll_reservation_byUser(String id) throws Exception;
}
