package design.lld.notification.repository;

import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.enums.NotificationType;
import design.lld.notification.model.IFormat;

import java.util.Map;

public class InMemoryMessageFormatRepository implements IMessageFormatRepository {

    private Map<NotificationType, Map<NotificationChannel, IFormat>> messageFormats;

    @Override
    public IFormat getMessageFormat(NotificationType type, NotificationChannel channel) {
        return messageFormats.get(type).get(channel);
    }
}
