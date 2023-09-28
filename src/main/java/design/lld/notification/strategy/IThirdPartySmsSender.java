package design.lld.notification.strategy;

public interface IThirdPartySmsSender {

    void sendSms(String message, String toPhone);
}
