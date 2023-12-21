package model;

import java.time.LocalDate;

public class ShippingDetail {
    private LocalDate shippingDate;
    private String shippingPlace;
    private String deliveryMethod;
    private String rentId;
    private String productId;
    private String customerId;

    public ShippingDetail() {
    	
    }

    public ShippingDetail(LocalDate shippingDate, String shippingPlace, String deliveryMethod,
                          String rentId, String productId, String customerId) {
        this.shippingDate = shippingDate;
        this.shippingPlace = shippingPlace;
        this.deliveryMethod = deliveryMethod;
        this.rentId = rentId;
        this.productId = productId;
        this.customerId = customerId;
    }

    // Getters and Setters
    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingPlace() {
        return shippingPlace;
    }

    public void setShippingPlace(String shippingPlace) {
        this.shippingPlace = shippingPlace;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ShippingDetail{" +
                "shippingDate=" + shippingDate +
                ", shippingPlace='" + shippingPlace + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", rentId='" + rentId + '\'' +
                ", productId='" + productId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
    
}
