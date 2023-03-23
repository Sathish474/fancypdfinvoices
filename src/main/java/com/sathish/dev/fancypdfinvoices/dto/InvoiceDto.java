package com.sathish.dev.fancypdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


public class InvoiceDto {

    @JsonProperty("user_id")
    @NotEmpty
    private String userId;

    @Min(value = 10, message = "Amount should not be less than 18")
    @Max(value = 1000, message = "Amount should not be greater than 150")
    private Integer amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "userId='" + userId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
