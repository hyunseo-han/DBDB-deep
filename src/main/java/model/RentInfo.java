package model;

import java.time.LocalDate;

public class RentInfo {
    private int rent_id; //자동생
    private int cstm_id; //
    private int prdt_id; //
    private int status; 
    private LocalDate start_day;
    private LocalDate end_day;
    private int rental_fee;
    
    // Product 정보
    private String title;
    private String photo;
    private String address;
    
    // Owner 정보
    private String ownerName; //닉네임 말고 찐 이름
    
    
    public RentInfo(int rent_id, int cstm_id, int prdt_id, int status, LocalDate start_day, LocalDate end_day,
            int rental_fee, String title, String photo, String address, String ownerName) {
        this.rent_id = rent_id;
        this.cstm_id = cstm_id;
        this.prdt_id = prdt_id;
        this.status = status;
        this.start_day = start_day;
        this.end_day = end_day;
        this.rental_fee = rental_fee;
        this.title = title;
        this.photo = photo;
        this.address = address;
        this.ownerName = ownerName;
    }
    
    
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getRent_id() {
        return rent_id;
    }
    public void setRent_id(int rent_id) {
        this.rent_id = rent_id;
    }
    public int getCstm_id() {
        return cstm_id;
    }
    public void setCstm_id(int cstm_id) {
        this.cstm_id = cstm_id;
    }
    public int getPrdt_id() {
        return prdt_id;
    }
    public void setPrdt_id(int prdt_id) {
        this.prdt_id = prdt_id;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public LocalDate getStart_day() {
        return start_day;
    }
    public void setStart_day(LocalDate start_day) {
        this.start_day = start_day;
    }
    public LocalDate getEnd_day() {
        return end_day;
    }
    public void setEnd_day(LocalDate end_day) {
        this.end_day = end_day;
    }
    public int getRental_fee() {
        return rental_fee;
    }
    public void setRental_fee(int rental_fee) {
        this.rental_fee = rental_fee;
    }



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
}