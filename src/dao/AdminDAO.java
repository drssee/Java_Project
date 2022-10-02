package dao;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;

import java.util.List;

public interface AdminDAO {
    Integer insertMovie_byAdmin(Movie movie) throws Exception;
    List<Movie> selectMovieList_byAdmin(PageRequest pageRequest) throws Exception;
    List<Movie> searchedMovieList_byAdmin(PageRequest pageRequest, String keyword) throws Exception;
    Integer getMovieCnt_byAdmin() throws Exception;
    Integer getMovieCnt_Searched_byAd(String keyword) throws Exception;
    Integer updateMovie_byAdmin(Movie movie) throws Exception;
    Integer deleteMovie_byAdmin(Movie movie) throws Exception;
    List<Reservation> selectAllRes(PageRequest pageRequest) throws Exception;
    Integer getResCnt() throws Exception;
    List<Reservation> searchedResList(PageRequest pageRequest, String keyword) throws Exception;
    Integer getResCnt_Searched(String keywrod) throws Exception;
    Integer updateRes(Reservation reservation) throws Exception;
    List<Reservation> selectResList_byTno(int tno) throws Exception;
}
