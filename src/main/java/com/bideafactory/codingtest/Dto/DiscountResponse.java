package com.bideafactory.codingtest.Dto;

public class DiscountResponse {


   private String  houseId;
   private String discountCode;
   private int id;
   private String userId;
   private boolean status;

    public DiscountResponse() {
    }

    public DiscountResponse(String houseId, String discountCode, int id, String userId, boolean status) {
        this.houseId = houseId;
        this.discountCode = discountCode;
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}



