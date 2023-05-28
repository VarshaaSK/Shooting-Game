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
public class VillainCharacterTest {

    @InjectMocks
    private Villain villain;

    @Test
    void checkIfVillainHealthIs100(){
        villain.setHealth(100);
        assertThat(villain.getHealth(),is(equalTo(100)));
    }

    @Test
    void checkIfVillainHealthIsReducedOnArmourState(){
        villain.setHealth(100);
        villain.setArmourState(true);
        villain.reduceHealth();
        assertThat(villain.getHealth(), is(equalTo(90)));
    }
}
