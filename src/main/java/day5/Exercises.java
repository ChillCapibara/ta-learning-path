package day5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercises {

    public static void main(String[] args){
        List<Person> peopleList = PeopleData.sample();

        List<Person> adults = peopleList.stream()
                .filter( p -> p.getAge() >= 18)
                .toList();

        List<String> descriptiveData = peopleList.stream()
                        .map(Person::toString)
                        .toList();

        Map<Integer, List<Person>> byAge = peopleList.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        List<String> allTags = peopleList.stream()
                        .flatMap(person -> person.getTags().stream())
                .toList();


        System.out.println(Arrays.toString(peopleList.toArray()));
        System.out.println(Arrays.toString(adults.toArray()));
        System.out.println(descriptiveData);
        System.out.println(byAge);
        System.out.println(allTags);
        System.out.println(peopleList.get(2).getAddress().getStreet().orElseGet(() -> "I need some sleep -_-"));
        //Compute fallback -> check if optional is empty -> decide if fallback should be used or not -> return value
        System.out.println(peopleList.get(1).getAddress().getStreet().orElse( PeopleData.getDefault()));
//        System.out.println(peopleList.get(3).getAddress().getStreet().orElse("optionalStreet")); <- this fails because the Address itself is null
    }
}
