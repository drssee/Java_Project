package service;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;
import util.MainServiceUtil;
import util.UserServiceUtil;

import java.sql.Timestamp;
import java.util.Date;
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
        return adminDAO.searchedMovieList_byAdmin(pageRequest,keyword);
    }
    @Override
    public Integer getMovieCnt() throws Exception {
        return adminDAO.getMovieCnt_byAdmin();
    }
    @Override
    public Integer getSearchedMovieCnt(String keyword) throws Exception {
        return adminDAO.getMovieCnt_Searched_byAd(keyword);
    }
    @Override
    public Integer updateMovie(Movie movie) throws Exception {
        return adminDAO.updateMovie_byAdmin(movie);
    }
    @Override
    public Integer deleteMovie(Movie movie) throws Exception {
        return adminDAO.deleteMovie_byAdmin(movie);
    }

    @Override
    public List<Reservation> getResList(PageRequest pageRequest) throws Exception {
        return adminDAO.selectAllRes(pageRequest);
    }

    @Override
    public Integer getResCnt() throws Exception {
        return adminDAO.getResCnt();
    }

    @Override
    public List<Reservation> getSearchedResList(PageRequest pageRequest, String keyword) throws Exception {
        return adminDAO.searchedResList(pageRequest,keyword);
    }

    @Override
    public Integer getSearchedResCnt(String keyword) throws Exception {
        return adminDAO.getResCnt_Searched(keyword);
    }

    @Override
    public Integer updateRes(Reservation reservation,int seatNum) throws Exception {
        reservation.setSeatNum(seatNum);
        reservation.setModDate(new Timestamp(new Date().getTime()));
        return adminDAO.updateRes(reservation);
    }

    @Override
    public boolean checkSeatNum(List<Reservation> reservationList,int num) throws Exception {
        if(reservationList==null||reservationList.size()==0||
                num<0||num>100){
            return false;
        }
        for(Reservation r : reservationList){
            if(r.getSeatNum()==num){
                return false;
            }
        }//for
        return true;
    }

    @Override
    public List<Reservation> getResList_byTno(int tno) throws Exception {
        return adminDAO.selectResList_byTno(tno);
    }

    @Override
    public boolean checkSchedule(Reservation reservation) throws Exception {
        //예약의 스케줄이 현재보다 미래일때만
        return reservation.getSchedule().after(new Date());
    }

    @Override
    public void deleteRes(Reservation reservation) throws Exception {
        int rno = reservation.getRno();
        User user = UserServiceUtil.INSTANCE.userService.selectOne(reservation.getId());
        int price = reservation.getPrice();
        MainServiceUtil.INSTANCE.mainService.deleteRes(rno,user,price);
    }
}
