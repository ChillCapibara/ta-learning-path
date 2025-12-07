package day5;

import java.util.List;

public class PeopleData {

    public static List<Person> sample() {
        return List.of(
                new Person(
                        "Alice",
                        30,
                        new Address("Warsaw", "Kwiatowa 12"),
                        List.of("admin", "premium", "beta-user")
                ),
                new Person(
                        "Bob",
                        17,
                        new Address("Krakow", "Florianska 8"),
                        List.of("new", "trial")
                ),
                new Person(
                        "Charlie",
                        25,
                        null,
                        List.of("premium", "early-access")
                ),
                new Person(
                        "Lili",
                        11,
                        null,
                        List.of("premium", "early-access")
                ),
                new Person(
                        "Diana",
                        40,
                        new Address("Gdansk", "Portowa 5"),
                        List.of("vip")
                ),
                new Person(
                        "Eve",
                        30,
                        new Address("Warsaw", "Szeroka 3"),
                        List.of("trial", "beta-user")
                )
        );
    }
}