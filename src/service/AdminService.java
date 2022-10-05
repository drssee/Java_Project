package service;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;

import java.util.List;

public interface AdminService extends Service {
    Integer insertMovie(Movie movie) throws Exception;
    List<Movie> getMovieList(PageRequest pageRequest) throws Exception;
    List<Movie> getSearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;
    Integer getMovieCnt() throws Exception;
    Integer getSearchedMovieCnt(String keyword) throws Exception;
    void updateMovie(Movie movie) throws Exception;
    void deleteMovie(Movie movie,List<String> IdList) throws Exception;
    List<Reservation> getResList(PageRequest pageRequest) throws Exception;
    Integer getResCnt() throws Exception;
    List<Reservation> getSearchedResList(PageRequest pageRequest,String keyword) throws Exception;
    Integer getSearchedResCnt(String keyword) throws Exception;
    Integer updateRes(Reservation reservation,int seatNum) throws Exception;
    boolean checkSeatNum(List<Reservation> reservationList,int num) throws Exception;
    List<Reservation> getResList_byTno(int tno) throws Exception;
    boolean checkSchedule(Reservation reservation) throws Exception;
    void deleteRes(Reservation reservation) throws Exception;
    List<String> getIdList_fromActivatedRes(Movie movie) throws Exception;
}
