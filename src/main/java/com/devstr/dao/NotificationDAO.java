package com.devstr.dao;

import com.devstr.model.Notification;
import java.math.BigInteger;
import java.util.List;

public interface NotificationDAO {

    BigInteger createNotification(String message, BigInteger receiverId);

    Notification readNotificationById(BigInteger id);

    List<Notification> readNotificationsByRecId(BigInteger receiverId);

    void deleteNotificationById(BigInteger id);

    String INSERT_NOTIFICATION = "INSERT INTO NOTIFICATION(MESSAGE, RECEIVER_ID) VALUES(?, ?)";

    String READ_NOTIFICATION_BY_ID = "SELECT NOTIFICATION_ID, MESSAGE, RECEIVER_ID FROM NOTIFICATION WHERE NOTIFICATION_ID = ?";

    String READ_NOTIFICATION_BY_REC_ID = "SELECT NOTIFICATION_ID, MESSAGE, RECEIVER_ID FROM NOTIFICATION WHERE RECEIVER_ID = ?";

    String DELETE_NOTIFICATION = "DELETE FROM NOTIFICATION WHERE NOTIFICATION_ID = ?";

}
