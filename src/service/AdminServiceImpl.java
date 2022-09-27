package service;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import dto.Movie;
import dto.PageRequest;
import dto.Reservation;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDAO adminDAO = new AdminDAOImpl();
    //등록
    @Override
    public Integer insertMovie(Movie movie) throws Exception{
        return adminDAO.insert(movie);
    }

    @Override
    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception {
        return adminDAO.selectMovieList(pageRequest);
    }

    @Override
    public List<Movie> getSearchedMovieList(PageRequest pageRequest, String keyword) throws Exception {
        return adminDAO.SearchedMovieList(pageRequest,keyword);
    }

    @Override
    public Integer getTotalCnt() throws Exception {
        return adminDAO.getCount();
    }

    @Override
    public Integer getTotalCnt_Searched(String keyword) throws Exception {
        return adminDAO.getCount_Searched(keyword);
    }

    @Override
    public Integer updateMovie(Movie movie) throws Exception {
        return adminDAO.update(movie);
    }

    @Override
    public Integer deleteMovie(Movie movie) throws Exception {
        return adminDAO.delete(movie);
    }
}
