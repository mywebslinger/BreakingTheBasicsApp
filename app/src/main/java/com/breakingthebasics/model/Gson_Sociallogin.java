package com.breakingthebasics.model;

import com.google.gson.annotations.SerializedName;

public class Gson_Sociallogin {

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
    Alldata alldata;

    public Alldata getAlldata() {
        return alldata;
    }

    public static class Alldata {
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

        @SerializedName("username")
        private String username = "username";

        public String getUsername() {
            return username;
        }

        @SerializedName("email")
        private String email = "email";

        public String getEmail() {
            return email;
        }

        @SerializedName("image")
        private String image = "image";

        public String getImage() {
            return image;
        }

        @SerializedName("subscription")
        private String subscription = "subscription";

        public String getSubscription() {
            return subscription;
        }

        @SerializedName("auth_token")
        private String auth_token = "auth_token";

        public String getAuth_token() {
            return auth_token;
        }

        @SerializedName("device_token")
        private String device_token = "device_token";

        public String getDevice_token() {
            return device_token;
        }

        @SerializedName("device_type")
        private String device_type = "device_type";

        public String getDevice_type() {
            return device_type;
        }

        @SerializedName("subscribe")
        private String subscribe = "subscribe";
    }

}
