package service;

import controller.MainController;
import dao.MainDAO;
import dao.MainDAOImpl;
import domain.Movie;
import domain.Reservation;
import domain.User;
import util.MainServiceUtil;
import util.UserServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainServiceImpl implements MainService{
    public MainServiceImpl() {}

    MainDAO mainDAO = new MainDAOImpl();

    @Override
    public List<Reservation> getReservationList(int tno) throws Exception {
        return mainDAO.selectAll_reservation(tno);
    }
    @Override
    public List<Reservation> getReservationList(String title) throws Exception {
        return mainDAO.selectAll_reservation(title);
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
    public Integer checkValidMovie(Movie movie, List<Reservation> reservations_byUser) {
        User loginedUser = MainController.loginedUser;
        //분단위 영화의 런타임을 초단위로 바꿈
        Long duration = Long.valueOf(movie.getRuntime()*60);
        Long scheduledTime = movie.getSchedule().getTime();
        for(int i=0;i<reservations_byUser.size();i++){
            //선택한 영화가 이미 내가 예매한 영화일경우
            if(reservations_byUser.get(i).getTno()==movie.getTno()){
                return -1;
            }
            Long reservatedTime = reservations_byUser.get(i).getSchedule().getTime();
            //나의 예매된 리스트의 상영시간과 지금 선택한 영화의 런타임이 겹칠때
            if(scheduledTime<=reservatedTime&&reservatedTime<=(scheduledTime+duration)){
                return -2;
            }
        }
        return 1;
    }
    @Override
    public List<String> getIdList_fromRes(Movie movie) throws Exception {
        List<Reservation> reservationList = getReservationList(movie.getTitle());
        List<String> idList = new ArrayList<>();
        for(Reservation r : reservationList){
            idList.add(r.getId());
        }
        return idList;
    }
    @Override
    public List<User> getUserList_fromRes(List<String> idList) throws Exception {
        //idlist로 userlist 가져와야함
        List<User> userList = new ArrayList<>();
        for(int i=0;i<idList.size();i++){
            User user = UserServiceUtil.INSTANCE.userService.selectOne(idList.get(i));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public String analysis(List<User> userList,String title) throws Exception {

        //gender age 통계 가져오기
        if(userList.size()<1){
            return null;
        }
        String result = "";
        //<gender1or2><count>
        Map<Integer,Integer> genderMap = mainDAO.groupByGender(userList,title);
        //<age><count>
        Map<Integer,Integer> ageMap = mainDAO.groupByAge(userList,title);

        //string으로 반환
        Set<Integer> keySet = genderMap.keySet();
        for(Integer key : keySet){
            Integer value = genderMap.get(key);
            result += (key==1?" [남성 ":" [여성 ")+value+"명]";
        }
        result += " / ";
        keySet = ageMap.keySet();
        for(Integer key : keySet){
            Integer value = ageMap.get(key);
            result += " ["+key+"0대 "+value+"명]";
        }
        return result+"\n";
    }

    @Override
    public String getAnalysis(Movie movie) throws Exception {
        List<String> idList = MainServiceUtil.INSTANCE.mainService.getIdList_fromRes(movie);
        List<User> userList = MainServiceUtil.INSTANCE.mainService.getUserList_fromRes(idList);
        String title = movie.getTitle();
        return MainServiceUtil.INSTANCE.mainService.analysis(userList,title);
    }
}
