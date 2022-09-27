package service;

import dao.AdminDAO;
import dto.Movie;
import dto.PageRequest;
import dto.Reservation;

import java.util.List;

public interface AdminService {
    //등록
    Integer insertMovie(Movie movie) throws Exception;

    List<Movie> getMovieList(PageRequest pageRequest) throws Exception;
    List<Movie> getSearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;

    Integer getTotalCnt() throws Exception;

    Integer getTotalCnt_Searched(String keyword) throws Exception;

    Integer updateMovie(Movie movie) throws Exception;

    Integer deleteMovie(Movie movie) throws Exception;
}
