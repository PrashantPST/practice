package design.lld.notification.service;

import design.lld.notification.model.Notification;
import design.lld.notification.model.SubscriptionService;
import design.lld.splitwise.models.User;

import java.util.List;

public class MarketingService {

    NotificationService notificationService;
    SubscriptionService subscriptionService;

    public void publishToSubscribers(String subscriptionKey, Notification notification) {
        final List<User> subscribers = subscriptionService.getSubscribers(subscriptionKey);
        subscribers.forEach(user -> {
            notificationService.sendNotification(notification, user);
        });
    }
}
