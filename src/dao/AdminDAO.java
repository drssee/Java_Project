package dao;

import domain.Movie;
import domain.PageRequest;

import java.util.List;

public interface AdminDAO {
    Integer insertMovie_byAdmin(Movie movie) throws Exception;
    List<Movie> selectMovieList_byAdmin(PageRequest pageRequest) throws Exception;
    List<Movie> SearchedMovieList_byAdmin(PageRequest pageRequest, String keyword) throws Exception;
    Integer getCount_byAdmin() throws Exception;
    Integer getCount_Searched_byAdmin(String keyword) throws Exception;
    Integer updateMovie_byAdmin(Movie movie) throws Exception;
    Integer deleteMovie_byAdmin(Movie movie) throws Exception;
}
