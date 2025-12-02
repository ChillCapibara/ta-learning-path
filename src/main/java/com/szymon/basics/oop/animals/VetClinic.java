package com.szymon.basics.oop.animals;

public class VetClinic {

    private Dog dog;
    private Dog fatDog;
    private Cat cat;
    private Cat fatCat;

    public VetClinic(){
        dog = new Dog();
        fatDog = new Dog();
        cat = new Cat();
        fatCat = new Cat();
    }

}
