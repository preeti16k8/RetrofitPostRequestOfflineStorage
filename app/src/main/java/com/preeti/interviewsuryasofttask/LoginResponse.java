package com.preeti.interviewsuryasofttask;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;

    @SerializedName("emailId")
    public String emailId;

    @SerializedName("imageUrl")
    public String imageUrl;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LoginResponse(String emailId) {
        this.emailId = emailId;
    }
}
