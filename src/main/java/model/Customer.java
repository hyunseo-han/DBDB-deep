package model;
import java.time.LocalDate;

public class Customer {
    private int customerId;
    private String name;
    private String passwd;
    private String address;
    private String email;
    private String phone;
    private String nickname;
    private int manner_score;
    private LocalDate birth_date;
  
    public Customer() {
        
    }

    public Customer(int customerId, String name, String passwd, String address, String email, String phone,
            String nickname, int manner_score, LocalDate birth_date) {
        super();
        this.customerId = customerId;
        this.name = name;
        this.passwd = passwd;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.manner_score = manner_score;
        this.birth_date = birth_date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getManner_score() {
        return manner_score;
    }

    public void setManner_score(int manner_score) {
        this.manner_score = manner_score;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
    
    
}
