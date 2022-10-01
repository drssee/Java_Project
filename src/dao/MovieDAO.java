package dao;

import domain.Movie;
import domain.PageRequest;

import java.util.List;

public interface MovieDAO {
    List<Movie> selectAll_byDate(PageRequest pageRequest) throws Exception;
    List<Movie> SearchList_byDate(PageRequest pageRequest,String keyword) throws Exception;
    Integer getTotalCount() throws Exception;
    Integer getSearchedTotalCount(String keyword) throws Exception;
}
