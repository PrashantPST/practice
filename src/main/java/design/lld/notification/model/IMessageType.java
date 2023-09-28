package design.lld.notification.model;

public interface IMessageType {

    EmailFormat getEmailFormat();
    SmsFormat getSmsFormat();
    PushFormat getPushFormat();
    InAppFormat getInAppFormat();
    IVRSFormat getIVRSFormat();
}
