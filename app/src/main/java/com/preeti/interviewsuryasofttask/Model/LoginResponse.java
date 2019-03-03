package com.preeti.interviewsuryasofttask.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {


    @SerializedName("items")
   public List<UserInformation> items;

    public List<UserInformation> getItems() {
        return items;
    }

    public void setItems(List<UserInformation> items) {
        this.items = items;
    }
}
