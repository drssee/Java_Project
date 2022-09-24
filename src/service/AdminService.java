package service;

import dao.AdminDAO;
import dto.Movie;
import dto.PageRequest;

import java.util.List;

public interface AdminService {
    //등록
    Integer insertMovie(Movie movie) throws Exception;

    List<Movie> getMovieList(PageRequest pageRequest) throws Exception;

    Integer getTotalCnt() throws Exception;
}
