package design.lld.notification.repository;

import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.enums.NotificationType;
import design.lld.notification.model.IFormat;

public interface IMessageFormatRepository {

    IFormat getMessageFormat(NotificationType type, NotificationChannel channel);
}
