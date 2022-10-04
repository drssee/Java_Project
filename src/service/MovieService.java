package service;

import domain.Movie;
import domain.PageRequest;
import domain.User;

import java.util.List;

public interface MovieService {
    List<Movie> getMovieList(PageRequest pageRequest) throws Exception;
    List<Movie> getSearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;
    Integer getTotalCnt() throws Exception;
    Integer getSearchedTotalCnt(String keyword) throws Exception;
}
