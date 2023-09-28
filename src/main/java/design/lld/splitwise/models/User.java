package design.lld.splitwise.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class User {
    /*
    user should have a userId, name, email, mobile number
     */
    private String id;
    private String name;
    private String email;
    private String phone;
}
