package dao;

import dto.Movie;
import dto.PageRequest;
import dto.User;

import java.util.List;

public interface UserDAO {
    User selectOne(String id) throws Exception;

    Integer insertUser(User user) throws Exception;

    List<Movie> selectAll_byDate(PageRequest pageRequest) throws Exception;
    List<Movie> SearchList_byDate(PageRequest pageRequest,String keyword) throws Exception;

    Integer getTotalCount() throws Exception;
    Integer getSearchedTotalCount(String keyword) throws Exception;
}
