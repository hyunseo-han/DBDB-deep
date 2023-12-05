package model;

import java.sql.Date;

//DBDB-deep에 맞게 수정
//파라미터 바꿔야 함
public class User {
    private int customerId;
    private int manner_score;
	private String name;
	private Date birth_date;
	private String nickname;
	private String email;
	private String passwd;
	private String phone;
	private String address;

	public User() { } // 기본 생성자
	
	public User(String name, Date birth_date, String nickname,  String email, String passwd,String phone, String address) {
		this.name = name;
		this.birth_date = birth_date;
		this.nickname = nickname;
		this.email = email;
		this.passwd = passwd;
		this.phone = phone;
		this.address = address;
	}
	
//	public User(String userId, String password, String name, String email, String phone, int commId, String commName) {
//		this(userId, password, name, email, phone, commId);
//		this.commName = commName;
//	}
//
//	public User(String userId, String name, String email, String phone) {
//		this.userId = userId;
//		this.name = name;
//		this.email = email;
//		this.phone = phone;		
//	}
	
	/*public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.email = updateUser.email;
        this.phone = updateUser.phone;
    }*/
	
	

	
	/* 비밀번호 검사 */
//	public boolean matchPassword(String password) {
//		if (password == null) {
//			return false;
//		}
//		return this.password.equals(password);
//	}
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = 999;
    }

    public int getManner_score() {
        return manner_score;
    }

    public void setManner_score(int manner_score) {
        this.manner_score = 0;
    }
    
    
//    public boolean isSameUser(String userid) {
//        return this.userId.equals(userid);
//    }
//
//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", phone="
//				+ phone + ", commId=" + commId + "]";
//	}	
}
