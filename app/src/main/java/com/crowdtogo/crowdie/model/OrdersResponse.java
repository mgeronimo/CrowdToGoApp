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

    public class Data{

    @Expose
    @SerializedName("orderId")
    private String orderId;
    @Expose
    @SerializedName("crowdieId")
    private String crowdieId;
    @Expose
    @SerializedName("firstname")
    private String firstname;
    @Expose
    @SerializedName("lastname")
    private String lastname;
    @Expose
    @SerializedName("street")
    private String street;
    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("aptNumber")
    private String aptNumber;
    @Expose
    @SerializedName("state")
    private String state;
    @Expose
    @SerializedName("zipcode")
    private String zipcode;
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
    @SerializedName("destinationlatitude")
    private double destinationlatitude;
    @Expose
    @SerializedName("destinationlongitude")
    private double destinationlongitude;
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
    @SerializedName("pickup_street_address")
    private String pickup_street_address;
    @Expose
    @SerializedName("pickup_address_line_2")
    private String pickup_address_line_2;
    @Expose
    @SerializedName("pickup_city")
    private String pickup_city;
    @Expose
    @SerializedName("pickup_state")
    private String pickup_state;
    @Expose
    @SerializedName("pickup_zip")
    private String pickup_zip;
    @Expose
    @SerializedName("pickuplatitude")
    private double pickuplatitude;
    @Expose
    @SerializedName("pickuplongitude")
    private double pickuplongitude;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCrowdieId() {
            return crowdieId;
        }

        public void setCrowdieId(String crowdieId) {
            this.crowdieId = crowdieId;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAptNumber() {
            return aptNumber;
        }

        public void setAptNumber(String aptNumber) {
            this.aptNumber = aptNumber;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
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

        public double getDestinationlatitude() {
            return destinationlatitude;
        }

        public void setDestinationlatitude(double destinationlatitude) {
            this.destinationlatitude = destinationlatitude;
        }

        public double getDestinationlongitude() {
            return destinationlongitude;
        }

        public void setDestinationlongitude(double destinationlongitude) {
            this.destinationlongitude = destinationlongitude;
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

        public String getPickup_street_address() {
            return pickup_street_address;
        }

        public void setPickup_street_address(String pickup_street_address) {
            this.pickup_street_address = pickup_street_address;
        }

        public String getPickup_address_line_2() {
            return pickup_address_line_2;
        }

        public void setPickup_address_line_2(String pickup_address_line_2) {
            this.pickup_address_line_2 = pickup_address_line_2;
        }

        public String getPickup_city() {
            return pickup_city;
        }

        public void setPickup_city(String pickup_city) {
            this.pickup_city = pickup_city;
        }

        public String getPickup_state() {
            return pickup_state;
        }

        public void setPickup_state(String pickup_state) {
            this.pickup_state = pickup_state;
        }

        public String getPickup_zip() {
            return pickup_zip;
        }

        public void setPickup_zip(String pickup_zip) {
            this.pickup_zip = pickup_zip;
        }

        public double getPickuplatitude() {
            return pickuplatitude;
        }

        public void setPickuplatitude(double pickuplatitude) {
            this.pickuplatitude = pickuplatitude;
        }

        public double getPickuplongitude() {
            return pickuplongitude;
        }

        public void setPickuplongitude(double pickuplongitude) {
            this.pickuplongitude = pickuplongitude;
        }
    }


}
