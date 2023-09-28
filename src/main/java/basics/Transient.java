package basics;

import java.io.Serializable;
import java.util.Date;

public class Transient implements Serializable {
    // Making password transient for security
    private transient String password;

    // Making age transient as age is auto-computable from DOB and current date.
    transient int age;

    // serialize other fields
    private String username, email;
    Date dob;

    // other code
}
