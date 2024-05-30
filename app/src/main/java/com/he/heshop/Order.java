package com.he.heShop;

public class Order {

    private String orderName;
    private String orderAddress;
    private String orderPaymentMethod;

    public Order(String orderName, String orderAddress, String orderPaymentMethod) {
        this.orderName = orderName;
        this.orderAddress = orderAddress;
        this.orderPaymentMethod = orderPaymentMethod;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getOrderPaymentMethod() {
        return orderPaymentMethod;
    }
}
