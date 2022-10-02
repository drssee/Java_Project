package domain;

import formatter.NumberFormatter;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    //선호장르 null가능
    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String email;
    private Date regDate;
    private int total_payment;
    private Timestamp modDate;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(int total_payment) {
        this.total_payment = total_payment;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }

    @Override
    public String toString() {
        return "아이디:"+getId()+" 이름:"+getName()+" 전화번호:"+getPhone()+" 이메일:"+getEmail()+" 등록일:"+ NumberFormatter.INSTANCE.formatDate(getRegDate())+" 총 결제금액:"+getTotal_payment();
    }
}
