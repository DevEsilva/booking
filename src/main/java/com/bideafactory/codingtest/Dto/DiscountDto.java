package com.bideafactory.codingtest.Dto;

import javax.validation.constraints.NotBlank;

public class DiscountDto {
@NotBlank String userId;
@NotBlank String houseId;
@NotBlank String discountCode;

    public DiscountDto( ) {

    }

    public DiscountDto(String userId, String houseId, String discountCode) {
        this.userId = userId;
        this.houseId = houseId;
        this.discountCode = discountCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
