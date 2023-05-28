package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class HeroCharacterTest {

    @InjectMocks
    private Hero hero;

    @Test
    void checkIfHeroHealthIs100(){
        hero.setHealth(100);
        assertThat(hero.getHealth(), is(equalTo(100)));
    }

    @Test
    void checkIfHeroHealthIsDecreased(){
        hero.setHealth(100);
        hero.reduceHealth();
        assertThat(hero.getHealth(),is(equalTo(80)));
    }
}
