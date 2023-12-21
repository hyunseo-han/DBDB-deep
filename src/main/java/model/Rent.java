package model;

import java.time.LocalDate;

public class Rent {
    private int rent_id;
    private int cstm_id;
    private int prdt_id;
    private int status;
    private LocalDate start_day;
    private LocalDate end_day;
    private int rental_fee;
    
    public Rent(int rent_id, int cstm_id, int prdt_id, int status, LocalDate start_day, LocalDate end_day,
            int rental_fee) {
        super();
        this.rent_id = rent_id;
        this.cstm_id = cstm_id;
        this.prdt_id = prdt_id;
        this.status = status;
        this.start_day = start_day;
        this.end_day = end_day;
        this.rental_fee = rental_fee;
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
}
