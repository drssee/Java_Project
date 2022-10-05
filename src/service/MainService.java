package service;

import domain.Movie;
import domain.Reservation;
import domain.User;

import java.util.List;

public interface MainService {
    List<Reservation> getReservationList(int tno) throws Exception;
    Integer getReservationCnt(int tno) throws Exception;
    void reservation(int selected, Movie movie, User user) throws Exception;
    List<Reservation> getReservationList_byUser(String id) throws Exception;
    void deleteRes(int rno,User user, int price) throws Exception;
    Integer checkValidMovie(Movie movie , List<Reservation> reservations_byUser)throws Exception;
    List<String> getIdList_fromRes(Movie movie) throws Exception;
    List<User> getUserList_fromRes(List<String> idList) throws Exception;
    String analysis(List<User> userList,int tno) throws Exception;
    String getAnalysis(Movie movie) throws Exception;
}
