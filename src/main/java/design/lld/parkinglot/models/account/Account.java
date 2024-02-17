package design.lld.parkinglot.models.account;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Account {

    private String id;
    private String email;
    private String userName;
    private String password;
    private LocalDateTime lastAccessed;
    private Contact contact;

}
