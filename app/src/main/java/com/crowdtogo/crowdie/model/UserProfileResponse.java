 package com.crowdtogo.crowdie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP Pav G4 on 1/14/2015.
 */
public class UserProfileResponse {

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
        @SerializedName("vehicle")
        private String vehicle;

        @Expose
        @SerializedName("completed_orders")
        private String completed_orders;

        @Expose
        @SerializedName("customer_rating")
        private String customer_rating;

        @Expose
        @SerializedName("merchant_rating")
        private String merchant_rating;

        @Expose
        @SerializedName("overall_rating")
        private String overall_rating;

        @Expose
        @SerializedName("rating_5")
        private String rating_5;

        @Expose
        @SerializedName("rating_4")
        private String rating_4;

        @Expose
        @SerializedName("rating_3")
        private String rating_3;

        @Expose
        @SerializedName("rating_2")
        private String rating_2;

        @Expose
        @SerializedName("rating_1")
        private String rating_1;

        @Expose
        @SerializedName("fastest_time")
        private String fastest_time;


        @Expose
        @SerializedName("address")
        private String address;

        public String getVehicle() {
            return vehicle;
        }

        public void setVehicle(String vehicle) {
            this.vehicle = vehicle;
        }

        public String getCompleted_orders() {
            return completed_orders;
        }

        public void setCompleted_orders(String completed_orders) {
            this.completed_orders = completed_orders;
        }

        public String getCustomer_rating() {
            return customer_rating;
        }

        public void setCustomer_rating(String customer_rating) {
            this.customer_rating = customer_rating;
        }

        public String getMerchant_rating() {
            return merchant_rating;
        }

        public void setMerchant_rating(String merchant_rating) {
            this.merchant_rating = merchant_rating;
        }

        public String getOverall_rating() {
            return overall_rating;
        }

        public void setOverall_rating(String overall_rating) {
            this.overall_rating = overall_rating;
        }

        public String getRating_5() {
            return rating_5;
        }

        public void setRating_5(String rating_5) {
            this.rating_5 = rating_5;
        }

        public String getRating_3() {
            return rating_3;
        }

        public void setRating_3(String rating_3) {
            this.rating_3 = rating_3;
        }

        public String getRating_4() {
            return rating_4;
        }

        public void setRating_4(String rating_4) {
            this.rating_4 = rating_4;
        }

        public String getRating_2() {
            return rating_2;
        }

        public void setRating_2(String rating_2) {
            this.rating_2 = rating_2;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getFastest_time() {
            return fastest_time;
        }

        public void setFastest_time(String fastest_time) {
            this.fastest_time = fastest_time;
        }

        public String getRating_1() {
            return rating_1;
        }

        public void setRating_1(String rating_1) {
            this.rating_1 = rating_1;
        }
    }


}
