package com.crowdtogo.crowdie.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {

    //@SuppressWarnings("serial")
   // public static class usersList extends ArrayList<Users> {
    //}
    @Expose
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


    public class Data{

        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("password")
        private String password;
        @Expose
        @SerializedName("status")
        private String status;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }



}
