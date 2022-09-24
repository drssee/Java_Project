package dto;

import formatter.DateFormatter;

import java.util.Date;

public class User {
    //선호장르 null가능
    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String email;
    private Date regDate;

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

    @Override
    public String toString() {
        return "아이디:"+getId()+" 비밀번호:"+getPwd()+" 이름:"+getName()+" 전화번호:"+getPhone()+" 이메일:"+getEmail()+" 등록일:"+ DateFormatter.INSTANCE.formatDate(getRegDate());
    }
}
