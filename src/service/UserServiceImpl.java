package service;

import controller.MainController;
import dao.UserDAO;
import dao.UserDAOImpl;
import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;

import java.util.List;

public class UserServiceImpl implements UserService { //서비스 인터페이스 상속
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public User selectOne(String id) throws Exception {
        return userDAO.selectOne(id);
    }

    @Override
    public Integer registerUser(User user) throws Exception {
        return userDAO.insertUser(user);
    }

    @Override
    public Integer updateUser(User user) throws Exception {
        return userDAO.updateUser(user);
    }

    @Override
    public List<Movie> getMovieList(PageRequest pageRequest) throws Exception {
        return userDAO.selectAll_byDate(pageRequest);
    }

    @Override
    public List<Movie> getSearchedMovieList(PageRequest pageRequest, String keyword) throws Exception {
        return userDAO.SearchList_byDate(pageRequest,keyword);
    }

    @Override
    public Integer getTotalCnt() throws Exception {
        return userDAO.getTotalCount();
    }

    @Override
    public Integer getSearchedTotalCnt(String keyword) throws Exception {
        return userDAO.getSearchedTotalCount(keyword);
    }

    @Override
    public List<Reservation> getReservationList(int tno) throws Exception {
        return userDAO.selectAll_reservation(tno);
    }

    @Override
    public Integer getReservationCnt(int tno) throws Exception {
        return userDAO.getReservationCount(tno);
    }

    @Override
    public void reservation(int selected,Movie movie) {
        //user와 reservation 객체를 넘겨준다
        User user = MainController.loginedUser;
        user.setTotal_payment(movie.getPrice());

        Reservation reservation = new Reservation();
        reservation.setTitle(movie.getTitle());
        reservation.setSchedule(movie.getSchedule());
        reservation.setSeatNum(selected);
        reservation.setTno(movie.getTno());
        reservation.setId(user.getId());
        reservation.setPrice(movie.getPrice());
        if(reservation!=null){
            userDAO.reservation(movie,reservation,user);
            return;
        }
//        throw new Exception("예약을 진행 할 수 없습니다");
    }

    @Override
    public List<Reservation> getReservationList_byUser(String id) throws Exception {
        return userDAO.selectAll_reservation_byUser(id);
    }

    @Override
    public void deleteRes(int rno,User user, int price) throws Exception {
        userDAO.deleteReservation(rno,user,price);
    }
}
