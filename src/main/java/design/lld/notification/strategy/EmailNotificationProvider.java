package design.lld.notification.strategy;

import design.lld.notification.model.EmailFormat;
import design.lld.notification.model.Notification;
import design.lld.notification.repository.IMessageFormatRepository;
import design.lld.notification.third_party.ThirdPartyEmailSender;

public class EmailNotificationProvider implements NotificationProvider {

  private ThirdPartyEmailSender thirdPartyEmailSender;
  private IMessageFormatRepository messageFormatRepository;

  @Override
  public void sendNotification(Notification notification) {

    final EmailFormat emailFormat = notification.getMessage().getEmailFormat();
    final EmailFormat emailFormat1 = (EmailFormat) messageFormatRepository.getMessageFormat(
        notification.getType(), notification.getChannel());
    thirdPartyEmailSender.sendEmail(emailFormat.getBody(), emailFormat.getSubject(),
        notification.getTo().getEmail(), null, null);
  }

}
