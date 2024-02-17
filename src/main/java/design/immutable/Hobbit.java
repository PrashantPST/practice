package design.immutable;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1) remove  setters
 * 2) add an all-args constructor
 * 3) mark class as final to protect it from being extended
 * 4) initialize all non-primitive mutable fields via constructor by performing a deep copy
 * 5) perform cloning of the returned non-primitive mutable object in getter methods
 * 6) marking all class fields as final (optional)
 */

final class Hobbit {

    @Getter
    private final String name;
    private final Address address;
    private final List<String> stuff;


    // all args constructor :=> Making deep copies of mutable objects in the constructor
    Hobbit(String name, Address address, List<String> stuff) {
        this.name = name;
        this.address = Address.builder().city(address.getCountry()).country(address.getCountry()).build();
        // Creating a defensive copy of the mutable List
        this.stuff = new ArrayList<>(stuff);
    }
    /*
        Returning deep copies of mutable objects in getter methods
     */
    Address getAddress() {
        return Address.builder().country(address.getCountry()).city(address.getCity()).build();
    }

    List<String> getStuff() {
        return new ArrayList<>(stuff);
    }
}
