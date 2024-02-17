package design.lld.notification.service;


import design.lld.notification.factory.NotificationProviderFactory;
import design.lld.notification.model.Notification;
import design.lld.notification.strategy.NotificationProvider;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

  @Autowired
  private NotificationProviderFactory notificationProviderFactory;

  public void sendNotification(@NonNull final Notification notification) {
    final NotificationProvider notificationProvider = notificationProviderFactory.getNotificationProvider(
        notification.getChannel());
    notificationProvider.sendNotification(notification);
  }
}
