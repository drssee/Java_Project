package dao;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;

import java.util.List;

public interface AdminDAO {
    Integer insertMovie_byAdmin(Movie movie) throws Exception;
    List<Movie> selectMovieList_byAdmin(PageRequest pageRequest) throws Exception;
    List<Movie> searchedMovieList_byAdmin(PageRequest pageRequest, String keyword) throws Exception;
    Integer getMovieCnt_byAdmin() throws Exception;
    Integer getMovieCnt_Searched_byAd(String keyword) throws Exception;
    void updateMovie_byAdmin(Movie movie) throws Exception;
    void deleteMovie_byAdmin(Movie movie,List<User> userList) throws Exception;
    List<Reservation> selectAllRes(PageRequest pageRequest) throws Exception;
    Integer getResCnt() throws Exception;
    List<Reservation> searchedResList(PageRequest pageRequest, String keyword) throws Exception;
    Integer getResCnt_Searched(String keywrod) throws Exception;
    Integer updateRes(Reservation reservation) throws Exception;
    List<Reservation> selectResList_byTno(int tno) throws Exception;
    List<String> selIdList_fromActivatedRes(int tno) throws Exception;
}
