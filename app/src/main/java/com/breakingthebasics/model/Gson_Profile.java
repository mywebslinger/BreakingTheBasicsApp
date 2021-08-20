package com.breakingthebasics.model;

import com.google.gson.annotations.SerializedName;

public class Gson_Profile {

    @SerializedName("status")
    private String status = "status";

    public String getStatus() {
        return status;
    }

    @SerializedName("message")
    private String message = "message";

    public String getMessage() {
        return message;
    }

    @SerializedName("data")
    CommonData alldata;

    public CommonData getAlldata() {
        return alldata;
    }
/*
    public class Alldata {
        @SerializedName("id")
        private String id = "id";

        public String getId() {
            return id;
        }

        @SerializedName("name")
        private String name = "name";

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getImage() {
            return image;
        }

        public String getSubscription() {
            return subscription;
        }

        public String getAuth_token() {
            return auth_token;
        }

        public String getDevice_token() {
            return device_token;
        }

        public String getDevice_type() {
            return device_type;
        }

       *//* public String getSubscribe() {
            return subscribe;
        }*//*

        @SerializedName("username")
        public String username="username";
        @SerializedName("email")
        public String email="email";
        @SerializedName("image")
        public String image="image";
        @SerializedName("subscription")
        public String subscription="subscription";
        @SerializedName("auth_token")
        public String auth_token="auth_token";
        @SerializedName("device_token")
        public String device_token="device_token";
        @SerializedName("device_type")
        public String device_type="device_type";

        *//*@SerializedName("subscribe")
        public String subscribe="subscribe";*//*
    }*/
}
