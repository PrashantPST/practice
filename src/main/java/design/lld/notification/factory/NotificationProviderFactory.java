package design.lld.notification.factory;


import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.strategy.AppNotificationProvider;
import design.lld.notification.strategy.EmailNotificationProvider;
import design.lld.notification.strategy.IVRSNotificationProvider;
import design.lld.notification.strategy.NotificationProvider;
import design.lld.notification.strategy.PushNotificationProvider;
import design.lld.notification.strategy.SMSNotificationProvider;

import java.util.HashMap;
import java.util.Map;

public class NotificationProviderFactory {

    private static final Map<NotificationChannel, NotificationProvider> notificationProvider = new HashMap<>();

    static {
        notificationProvider.put(NotificationChannel.APP, new AppNotificationProvider());
        notificationProvider.put(NotificationChannel.EMAIL, new EmailNotificationProvider());
        notificationProvider.put(NotificationChannel.SMS, new SMSNotificationProvider());
        notificationProvider.put(NotificationChannel.PUSH, new PushNotificationProvider());
        notificationProvider.put(NotificationChannel.IVRS, new IVRSNotificationProvider());
    }

    private NotificationProviderFactory() {
    }

    public static NotificationProvider getNotificationProvider(NotificationChannel channel) {
        return notificationProvider.get(channel);
    }
}
