package design.lld.parkinglot.models.account;


import lombok.Setter;

@Setter
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pinCode;
}
