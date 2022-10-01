package service;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import domain.Movie;
import domain.PageRequest;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDAO adminDAO = new AdminDAOImpl();


    @Override
    public Integer insertMovie(Movie movie) throws Exception{
        return adminDAO.insertMovie_byAdmin(movie);
    }
    @Override
    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception {
        return adminDAO.selectMovieList_byAdmin(pageRequest);
    }
    @Override
    public List<Movie> getSearchedMovieList(PageRequest pageRequest, String keyword) throws Exception {
        return adminDAO.SearchedMovieList_byAdmin(pageRequest,keyword);
    }
    @Override
    public Integer getTotalCnt() throws Exception {
        return adminDAO.getCount_byAdmin();
    }
    @Override
    public Integer getTotalCnt_Searched(String keyword) throws Exception {
        return adminDAO.getCount_Searched_byAdmin(keyword);
    }
    @Override
    public Integer updateMovie(Movie movie) throws Exception {
        return adminDAO.updateMovie_byAdmin(movie);
    }
    @Override
    public Integer deleteMovie(Movie movie) throws Exception {
        return adminDAO.deleteMovie_byAdmin(movie);
    }
}
