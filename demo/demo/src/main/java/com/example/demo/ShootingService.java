package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Service
public class ShootingService {

    @Autowired
    private Hero heroFunction;

    @Autowired
    private Villain villainFunction;

    int getCharacterHealth(String character){
        int charHealth = 0;
        System.out.println(character.toUpperCase());
        if(character.toUpperCase().equals("HERO")){
            charHealth = heroFunction.getHealth();
            System.out.println(1);
        }
        else{
            charHealth = villainFunction.getHealth();
        }
        return charHealth;
    }

    void characterAttack(String role){
        if(role.toUpperCase().equals("HERO")){
            villainFunction.reduceHealth();
        }
        if(role.toUpperCase().equals("VILLAIN")){
            heroFunction.reduceHealth();
        }
    }

    public void reduceHealthOnArmourState(boolean state) {
        villainFunction.setArmourState(state);
    }

    public void restartServer(){
        villainFunction.setHealth(100);
        heroFunction.setHealth(100);
    }
}
