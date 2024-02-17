package design.lld.notification.strategy;

import design.lld.notification.model.Notification;

public interface NotificationProvider {

  void sendNotification(Notification notification);
}
