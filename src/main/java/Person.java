
public class Person extends SuperPerson implements Comparable<Person>{

    private String name;
    private Integer age;
    private Integer height;

    public Person(String name, Integer age, Integer height){
        this.name=name;
        this.age=age;
        this.height=height;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public int compareTo(Person o) {
        if (this.age == o.age)
            return this.name.compareTo(o.name);
        return this.age > o.age ? 1 : -1;
    }
}
