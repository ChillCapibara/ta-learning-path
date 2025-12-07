package day5;

import java.util.List;

public class Person {
    private final String name;
    private final int age;
    private final Address address;
    private final List<String> tags;

    public Person(String name, int age, Address address, List<String> tags) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.tags = tags;
    }

    public String getName() { return name; }

    public int getAge() { return age; }

    public Address getAddress() { return address; }

    public List<String> getTags() { return tags; }

    @Override
    public String toString() {
        return name + " (" + age + ") - " +
                (address != null ? address.getCity() : "no city");
    }
}
