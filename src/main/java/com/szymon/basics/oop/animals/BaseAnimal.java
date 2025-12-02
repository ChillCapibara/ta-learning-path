package com.szymon.basics.oop.animals;

public class BaseAnimal implements Animal {


    public BaseAnimal(String... sound){
        makeSound(sound);
    }

    @Override
    public void makeSound(String... sound) {
        StringBuilder animalSound = new StringBuilder();
        for (String noise : sound) {
            animalSound.append(" ").append(noise);
        }
        System.out.println(animalSound);
    }

}
