package service;

import domain.Movie;
import domain.Reservation;
import domain.User;

import java.util.List;

public interface MainService {
    public List<Reservation> getReservationList(int tno) throws Exception;
    public Integer getReservationCnt(int tno) throws Exception;
    public void reservation(int selected, Movie movie, User user) throws Exception;
    public List<Reservation> getReservationList_byUser(String id) throws Exception;
    public void deleteRes(int rno,User user, int price) throws Exception;
    public boolean checkTime(Movie movie , List<Reservation> reservations_byUser);
}
