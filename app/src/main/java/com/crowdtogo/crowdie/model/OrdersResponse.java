package com.crowdtogo.crowdie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alj on 12/5/2014.
 */
public class OrdersResponse {

    @Expose
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        @Expose
        @SerializedName("orderId")
        private String orderId;
        @Expose
        @SerializedName("firstname")
        private String firstname;
        @Expose
        @SerializedName("lastname")
        private String lastname;
        @Expose
        @SerializedName("destination_address")
        private String destination_address;
        @Expose
        @SerializedName("contact")
        private String contact;
        @Expose
        @SerializedName("size")
        private String size;
        @Expose
        @SerializedName("status")
        private String status;
        @Expose
        @SerializedName("destination_latitude")
        private String destination_latitude;
        @Expose
        @SerializedName("destination_longitude")
        private String destination_longitude;
        @Expose
        @SerializedName("merchantId")
        private String merchantId;
        @Expose
        @SerializedName("store_name")
        private String store_name;
        @Expose
        @SerializedName("store_contact")
        private String store_contact;
        @Expose
        @SerializedName("pickup_address")
        private String pickup_address;
        @Expose
        @SerializedName("pickup_address_line_2")
        private String pickup_address_line_2;
        @Expose
        @SerializedName("pickup_latitude")
        private String pickup_latitude;
        @Expose
        @SerializedName("pickup_longitude")
        private String pickup_longitude;
        @Expose
        @SerializedName("pickup_date")
        private String pickup_date;
        @Expose
        @SerializedName("pickup_time")
        private String pickup_time;

        @Expose
        @SerializedName("groupId")
        private String groupId;
        @Expose
        @SerializedName("deliveryStatus")
        private String deliveryStatus;
        @Expose
        @SerializedName("duration")
        private String duration;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPickup_date() {
            return pickup_date;
        }

        public void setPickup_date(String pickup_date) {
            this.pickup_date = pickup_date;
        }

        public String getPickup_time() {
            return pickup_time;
        }

        public void setPickup_time(String pickup_time) {
            this.pickup_time = pickup_time;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getDestination_address() {
            return destination_address;
        }

        public void setDestination_address(String destination_address) {
            this.destination_address = destination_address;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDestination_latitude() {
            return destination_latitude;
        }

        public void setDestination_latitude(String destination_latitude) {
            this.destination_latitude = destination_latitude;
        }

        public String getDestination_longitude() {
            return destination_longitude;
        }

        public void setDestination_longitude(String destination_longitude) {
            this.destination_longitude = destination_longitude;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_contact() {
            return store_contact;
        }

        public void setStore_contact(String store_contact) {
            this.store_contact = store_contact;
        }

        public String getPickup_address() {
            return pickup_address;
        }

        public void setPickup_address(String pickup_address) {
            this.pickup_address = pickup_address;
        }

        public String getPickup_address_line_2() {
            return pickup_address_line_2;
        }

        public void setPickup_address_line_2(String pickup_address_line_2) {
            this.pickup_address_line_2 = pickup_address_line_2;
        }

        public String getPickup_latitude() {
            return pickup_latitude;
        }

        public void setPickup_latitude(String pickup_latitude) {
            this.pickup_latitude = pickup_latitude;
        }

        public String getPickup_longitude() {
            return pickup_longitude;
        }

        public void setPickup_longitude(String pickup_longitude) {
            this.pickup_longitude = pickup_longitude;
        }

        /*

"orderId": 3,
"firstname": "Ivann",
"lastname": "Magadia",
"destination_address": "15 Mocha Bacoor City AL 12345",
"contact": "091928454548",
"size": "Car",
"status": 1,
"destination_latitude": 36.075614,
"destination_longitude": -94.169575,
"merchantId": 3,
"store_name": "Renan Flower Shop",
"store_contact": null,
"pickup_address": "Chestnut Bacoor City AL 1234512345",
"pickup_address_line_2": "Blk. 28 Lot. 24 Ph.4 Soldiers Hills Molino Bacoor Cavite",
"pickup_latitude": 36.079549,
"pickup_longitude": -94.1749507


         */

    }
}
