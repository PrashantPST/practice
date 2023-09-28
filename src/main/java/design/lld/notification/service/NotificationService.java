package design.lld.notification.service;


import design.lld.notification.factory.NotificationProviderFactory;
import design.lld.notification.model.Notification;
import design.lld.notification.strategy.NotificationProvider;
import design.lld.notification.third_party.ThirdPartyEmailSender;
import design.lld.notification.third_party.ThirdPartySmsSender;
import design.lld.splitwise.models.User;
import lombok.NonNull;

public class NotificationService {
    private NotificationProviderFactory notificationProviderFactory;
    private ThirdPartyEmailSender thirdPartyEmailSender;
    private ThirdPartySmsSender thirdPartySmsSender;

    public void sendNotification(@NonNull final Notification notification, User user) {

        final NotificationProvider notificationProvider = NotificationProviderFactory.getNotificationProvider(notification.getChannel());
        notificationProvider.sendNotification(notification);
    }
}
