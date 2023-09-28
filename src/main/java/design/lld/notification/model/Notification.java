package design.lld.notification.model;

import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.enums.NotificationType;
import design.lld.splitwise.models.User;
import design.lld.notification.enums.NotificationPriorityType;
import lombok.Getter;

import java.util.Date;


@Getter
public class Notification {

    private String id;
    private IMessageType message;
    private NotificationChannel channel;
    private NotificationType type;
    private User from;
    private User to;
    private Date createdAt;
    private NotificationPriorityType priorityType;
}
