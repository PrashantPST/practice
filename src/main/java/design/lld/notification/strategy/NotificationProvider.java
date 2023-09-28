package design.lld.notification.strategy;

import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.model.Notification;

public interface NotificationProvider {

    void sendNotification(Notification notification);
    NotificationChannel getChannel();
}
