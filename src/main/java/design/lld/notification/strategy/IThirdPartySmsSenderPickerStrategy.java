package design.lld.notification.strategy;

import design.lld.notification.model.Notification;

import java.util.List;

public interface IThirdPartySmsSenderPickerStrategy {
    IThirdPartySmsSender pick(List<IThirdPartySmsSender> smsSenders, Notification notification);
}
