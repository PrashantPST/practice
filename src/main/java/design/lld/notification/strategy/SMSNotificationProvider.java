package design.lld.notification.strategy;

import design.lld.notification.enums.NotificationChannel;
import design.lld.notification.model.Notification;
import design.lld.notification.model.SmsFormat;
import design.lld.notification.third_party.ThirdPartySmsSender;
import design.lld.notification.third_party.ThirdPartySmsSenderHighRelHighCost;
import design.lld.notification.third_party.ThirdPartySmsSenderLowRelLowCost;

import java.util.List;

public class SMSNotificationProvider implements NotificationProvider {

    private ThirdPartySmsSender thirdPartySmsSender;

    private ThirdPartySmsSenderHighRelHighCost thirdPartySmsSenderHighRelHighCost;
    private ThirdPartySmsSenderLowRelLowCost thirdPartySmsSenderLowRelLowCost;
    private IThirdPartySmsSenderPickerStrategy thirdPartySmsSenderPickerStrategy;

    private List<IThirdPartySmsSender> smsSenders;
    @Override
    public void sendNotification(Notification notification) {
        final SmsFormat smsFormat = notification.getMessage().getSmsFormat();
        thirdPartySmsSender.sendSms(smsFormat.getBody(), notification.getTo().getPhone());


        //        if (notification.getPriorityType() == TRANSACTIONAL) {
//            thirdPartySmsSenderHighRelHighCost.sendSms();
//        }

        IThirdPartySmsSender tpSmsSender = thirdPartySmsSenderPickerStrategy.pick(smsSenders, notification);
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.SMS;
    }
}
