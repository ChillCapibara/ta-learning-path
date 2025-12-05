public class SubPerson extends Person{

    private String randomField;



    public SubPerson(String name, Integer age, Integer height) {
        super(name, age, height);
    }
    public String getRandomField() {
        return randomField;
    }
}
