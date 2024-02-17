package design.lld.notification.service;

import design.lld.notification.model.Notification;
import design.lld.splitwise.models.User;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionService {

  private final ConcurrentHashMap<String, CopyOnWriteArrayList<String>> subscriptions;

  @Autowired
  private NotificationService notificationService;

  public SubscriptionService(
      ConcurrentHashMap<String, CopyOnWriteArrayList<String>> subscriptions) {
    this.subscriptions = subscriptions;
  }

  public void subscribe(User subscriber, String subscriptionKey) {
    String userId = subscriber.getId();
    this.subscriptions.computeIfAbsent(subscriptionKey, k -> new CopyOnWriteArrayList<>())
        .add(userId);
  }

  public void unsubscribe(String subscriptionKey, User subscriber) {
    String userId = subscriber.getId();
    this.subscriptions.computeIfPresent(subscriptionKey, (key, subscribers) -> {
      subscribers.remove(userId);
      return subscribers;
    });
  }

  public CopyOnWriteArrayList<String> getSubscribers(String subscriptionKey) {
    return this.subscriptions.getOrDefault(subscriptionKey, new CopyOnWriteArrayList<>());
  }

  public void publishToSubscribers(String subscriptionKey, Notification notification) {
    final CopyOnWriteArrayList<String> subscribers = getSubscribers(subscriptionKey);
    subscribers.forEach(user -> notificationService.sendNotification(notification));
  }
}
