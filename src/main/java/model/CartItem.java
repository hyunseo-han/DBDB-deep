package model;

import java.util.List;

public class CartItem {
    private int customerId;
    private int productId;
    private int quantity;
    private int rentalFee;
    private List<CartItem> cartList;
    
    public CartItem() {}
    
    public CartItem(int customerId, int productId, int quantity, int rentalFee) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.rentalFee = rentalFee;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getRentalFee() {
        return rentalFee;
    }
    public void setRentalFee(int rentalFee) {
        this.rentalFee = rentalFee;
    }

    @Override
    public String toString() {
        return "CartItem [customerId=" + customerId + ", productId=" + productId + ", quantity=" + quantity
                + ", rentalFee=" + rentalFee + "]";
    }

    public List<CartItem> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartItem> cartList) {
        this.cartList = cartList;
    }
}
