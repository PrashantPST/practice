package design.lld.splitwise.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class User {

  private String id;
  private String name;
  private String email;
  private String mobileNumber;
}
