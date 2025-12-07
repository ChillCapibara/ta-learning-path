package day5;

import java.util.Optional;

public class Address {
    private final String city;
    private final String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public String getCity() { return city; }

    public Optional<String> getStreet() { return Optional.ofNullable(street); }
}
