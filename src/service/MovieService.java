package service;

import domain.Movie;
import domain.PageRequest;

import java.util.List;

public interface MovieService {
    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception;
    public List<Movie> getSearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;
    public Integer getTotalCnt() throws Exception;
    public Integer getSearchedTotalCnt(String keyword) throws Exception;
}
