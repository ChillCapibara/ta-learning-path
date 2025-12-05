import java.util.*;

public class Day4 {

    public static void main (String[] args){

        List<Person> personList = new ArrayList<>();
        List<Person> personList2 = new ArrayList<>();
        personList.add(new Person("John", 20, 180));
        personList.add(new Person("John", 30, 180));
        personList.add(new Person("Amy", 30, 180));
        personList.add(new Person("Jonathan", 30, 180));
        personList.add(new Person("Tina", 30, 180));
        personList.add(new Person("Turner", 15, 180));
        personList.add(new Person("Alex", 22, 180));
        personList.add(new Person("John", 22, 180));
        personList.add(new Person("Liz", 80, 180));
        personList.add(new Person("Bob", 22, 170));

//        Collections.sort(personList);
//        for (Person p : personList){
//            System.out.println(p.getName() + ": " + p.getAge());
//        }

//        personList.sort(new NameComparator());
//        personList.sort(new AgeComparator());
//        SortUtils.sort(personList, new NameComparator());
//        for (Person p : personList){
//            System.out.println(p.getName() + ": " + p.getAge());
//        }
//        Person oldest = SortUtils.max(personList, new AgeComparator());
//        Person oldest = SortUtils.max(personList2, new AgeComparator());
//        System.out.println(oldest.getName() + ": " + oldest.getAge());


        List<? extends Person> list = new ArrayList<>();
//        list.add(new SuperPerson());
//        list.add(new SuperPerson());
//        list.add(null);
//        list.add(new Person("lorem", 11, 111));
//        list.add(new SubPerson("lorem", 11, 111));
//        SuperPerson p = list.get(0);

    }

}
