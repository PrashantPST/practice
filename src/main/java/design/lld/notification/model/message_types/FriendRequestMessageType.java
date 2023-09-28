package design.lld.notification.model.message_types;

import design.lld.notification.model.EmailFormat;
import design.lld.notification.model.IMessageType;
import design.lld.notification.model.IVRSFormat;
import design.lld.notification.model.InAppFormat;
import design.lld.notification.model.PushFormat;
import design.lld.notification.model.SmsFormat;


/*
    Hi $first_name$, You received a friend request.
 */
public class FriendRequestMessageType implements IMessageType {
    @Override
    public EmailFormat getEmailFormat() {
        return null;
    }
    @Override
    public SmsFormat getSmsFormat() {
        return null;
    }
    @Override
    public PushFormat getPushFormat() {
        return null;
    }

    @Override
    public InAppFormat getInAppFormat() {
        return null;
    }

    @Override
    public IVRSFormat getIVRSFormat() {
        return null;
    }
}
