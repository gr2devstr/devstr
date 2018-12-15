package com.devstr.model.impl;

import com.devstr.model.Notification;

import java.math.BigInteger;

public class NotificationImpl implements Notification {

    private BigInteger notificationId;
    private String message;
    private BigInteger receiverId;

    @Override
    public BigInteger getNotificationId() {
        return notificationId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public BigInteger getReceiverId() {
        return receiverId;
    }

    public static class Builder {
        private BigInteger notificationId;
        private String message;
        private BigInteger receiverId;

        public Builder(String message, BigInteger receiverId) {
            this.message = message;
            this.receiverId = receiverId;
        }

        public Builder setNotificationId(BigInteger notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public Notification build() {return new NotificationImpl(this);}
    }

    private NotificationImpl(Builder builder) {
        this.notificationId = builder.notificationId;
        this.message = builder.message;
        this.receiverId = builder.receiverId;
    }
}
