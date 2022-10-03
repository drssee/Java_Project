package service;

import dao.MainDAO;
import dao.MainDAOImpl;
import domain.Movie;
import domain.Reservation;
import domain.User;

import java.util.List;

public class MainServiceImpl implements MainService{
    public MainServiceImpl() {}

    MainDAO mainDAO = new MainDAOImpl();

    @Override
    public List<Reservation> getReservationList(int tno) throws Exception {
        return mainDAO.selectAll_reservation(tno);
    }
    @Override
    public Integer getReservationCnt(int tno) throws Exception {
        return mainDAO.getReservationCount(tno);
    }
    @Override
    public void reservation(int selected, Movie movie, User user) throws Exception {
        //user와 reservation 객체를 넘겨준다
        user.setTotal_payment(user.getTotal_payment()+movie.getPrice());

        Reservation reservation = new Reservation();
        reservation.setTitle(movie.getTitle());
        reservation.setSchedule(movie.getSchedule());
        reservation.setSeatNum(selected);
        reservation.setTno(movie.getTno());
        reservation.setId(user.getId());
        reservation.setPrice(movie.getPrice());
        if(reservation==null){
            //controller로 예외를 던짐
            throw new Exception();
        }
        mainDAO.reservation(movie,reservation,user);
    }
    @Override
    public List<Reservation> getReservationList_byUser(String id) throws Exception {
        return mainDAO.selectAll_reservation_byUser(id);
    }
    @Override
    public void deleteRes(int rno,User user, int price) throws Exception {
        mainDAO.deleteReservation(rno,user,price);
    }
    @Override
    public boolean checkTime(Movie movie,List<Reservation> reservations_byUser) {
        //분단위 영화의 런타임을 초단위로 바꿈
        Long duration = Long.valueOf(movie.getRuntime()*60);
        Long scheduledTime = movie.getSchedule().getTime();
        for(int i=0;i<reservations_byUser.size();i++){
            Long reservatedTime = reservations_byUser.get(i).getSchedule().getTime();
            //예약된 리스트의 상영시간들중에 ,
            //지금 예약하려고 고른 영화의 런타임이 겹칠때
            if(scheduledTime<=reservatedTime&&reservatedTime<=(scheduledTime+duration)){
                return false;
            }
        }
        return true;
    }
}
