package service;

import dao.MovieDAO;
import dao.MovieDAOImpl;
import domain.Movie;
import domain.PageRequest;

import java.util.List;

public class MovieServiceImpl implements MovieService{
    MovieDAO movieDAO = new MovieDAOImpl();

    @Override
    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception {
        return movieDAO.selectAll_byDate(pageRequest);
    }

    @Override
    public List<Movie> getSearchedMovieList(PageRequest pageRequest, String keyword) throws Exception {
        return movieDAO.SearchList_byDate(pageRequest,keyword);
    }

    @Override
    public Integer getTotalCnt() throws Exception {
        return movieDAO.getTotalCount();
    }

    @Override
    public Integer getSearchedTotalCnt(String keyword) throws Exception {
        return movieDAO.getSearchedTotalCount(keyword);
    }
}
