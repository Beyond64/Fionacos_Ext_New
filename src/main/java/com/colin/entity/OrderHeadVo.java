package com.colin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderHeadVo {
    private Long orderNumber;
    private Integer  productsNumber;
    private String  orderCreator;
    private String  deliveryTo;
    private String  orderStatus;
    private Date orderCreateTime;
    private Date  orderApprovalTime;
    private String  trackingNumber;
    private String  trackingName;
    private Long purchaseOrderNo;
    private String purchaseOrderType;
    private String deliveryToDesc;

    public Long getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(Long purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getPurchaseOrderType() {
        return purchaseOrderType;
    }

    public void setPurchaseOrderType(String purchaseOrderType) {
        this.purchaseOrderType = purchaseOrderType;
    }

    public String getDeliveryToDesc() {
        return deliveryToDesc;
    }

    public void setDeliveryToDesc(String deliveryToDesc) {
        this.deliveryToDesc = deliveryToDesc;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(Integer productsNumber) {
        this.productsNumber = productsNumber;
    }

    public String getOrderCreator() {
        return orderCreator;
    }

    public void setOrderCreator(String orderCreator) {
        this.orderCreator = orderCreator;
    }

    public String getDeliveryTo() {
        return deliveryTo;
    }

    public void setDeliveryTo(String deliveryTo) {
        this.deliveryTo = deliveryTo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getOrderApprovalTime() {
        return orderApprovalTime;
    }

    public void setOrderApprovalTime(Date orderApprovalTime) {
        this.orderApprovalTime = orderApprovalTime;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingName() {
        return trackingName;
    }

    public void setTrackingName(String trackingName) {
        this.trackingName = trackingName;
    }
}
