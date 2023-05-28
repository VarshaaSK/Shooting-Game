package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Hero {

    int health = 100;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void reduceHealth() {
        int damage = 20;
        this.setHealth(getHealth()-damage);
    }
}
