package com.devstr.model;

import java.math.BigInteger;

public interface Notification {

    BigInteger getNotificationId();

    String getMessage();

    BigInteger getReceiverId();
}
