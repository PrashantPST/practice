package design.lld.notification.strategy;

import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.model.Notification;

public class IVRSNotificationProvider implements NotificationProvider {

    @Override
    public void sendNotification(Notification notification) {

    }

    @Override
    public NotificationChannel getChannel() {
        return null;
    }
}