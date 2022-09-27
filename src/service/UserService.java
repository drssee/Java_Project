package service;

import dto.Movie;
import dto.PageRequest;
import dto.User;

import java.util.List;

public interface UserService {
    public User selectOne(String id) throws Exception;

    public Integer registerUser(User user) throws Exception;

    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception;
    public List<Movie> getSearchedMovieList(PageRequest pageRequest,String keyword) throws Exception;

    public Integer getTotalCnt() throws Exception;

    public Integer getSearchedTotalCnt(String keyword) throws Exception;
}
