package design.lld.notification.model;


import lombok.Getter;

@Getter
public class EmailFormat implements IFormat {

    String subject;
    String body;
}
