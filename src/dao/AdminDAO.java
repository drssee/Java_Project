package dao;

import domain.Movie;
import domain.PageRequest;

import java.util.List;

public interface AdminDAO {
    Integer insert(Movie movie) throws Exception;

    List<Movie> selectMovieList(PageRequest pageRequest) throws Exception;
    List<Movie> SearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;

    Integer getCount() throws Exception;
    Integer getCount_Searched(String keyword) throws Exception;

    Integer update(Movie movie) throws Exception;

    Integer delete(Movie movie) throws Exception;
}
