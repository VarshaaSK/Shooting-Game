package com.example.demo;

import org.springframework.stereotype.Component;


@Component
public class Villain extends Hero {

    private boolean armourState = true;

    public boolean getArmourState() {
        return armourState;
    }

    public void setArmourState(boolean armourState) {
        this.armourState = armourState;
    }
    @Override
    public void reduceHealth(){
        int damage;
        if(this.getArmourState()){
            damage = 10;
        }
        else{
            damage = 20;
        }
        super.setHealth(getHealth()-damage);
    }
}
