package dao;

import dto.Movie;
import dto.PageRequest;

import java.util.List;

public interface AdminDAO {
    Integer insert(Movie movie) throws Exception;

    List<Movie> selectMovieList(PageRequest pageRequest) throws Exception;

    Integer getCount()throws Exception;
}
