package design.lld.notification.factory;


import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.strategy.AppNotificationProvider;
import design.lld.notification.strategy.EmailNotificationProvider;
import design.lld.notification.strategy.IVRSNotificationProvider;
import design.lld.notification.strategy.NotificationProvider;
import design.lld.notification.strategy.PushNotificationProvider;
import design.lld.notification.strategy.SMSNotificationProvider;

import java.util.Map;

public class NotificationProviderFactory {

    private static final Map<NotificationChannel, NotificationProvider> NOTIFICATION_PROVIDERS;

    static {
        NOTIFICATION_PROVIDERS = Map.of(
                NotificationChannel.APP, new AppNotificationProvider(),
                NotificationChannel.EMAIL, new EmailNotificationProvider(),
                NotificationChannel.SMS, new SMSNotificationProvider(),
                NotificationChannel.PUSH, new PushNotificationProvider(),
                NotificationChannel.IVRS, new IVRSNotificationProvider()
        );
    }

    private NotificationProviderFactory() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves the notification provider for the specified channel.
     * @param channel The notification channel.
     * @return The notification provider for the given channel.
     * @throws IllegalArgumentException if the channel is not supported.
     */
    public NotificationProvider getNotificationProvider(NotificationChannel channel) {
        NotificationProvider provider = NOTIFICATION_PROVIDERS.get(channel);
        if (provider == null) {
            throw new IllegalArgumentException("Unsupported notification channel: " + channel);
        }
        return provider;
    }
}



