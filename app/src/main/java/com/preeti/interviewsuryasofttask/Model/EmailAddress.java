package com.preeti.interviewsuryasofttask.Model;

import com.google.gson.annotations.SerializedName;

public class EmailAddress {

    @SerializedName("emailId")
    private  String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
