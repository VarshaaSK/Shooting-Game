package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Import(ShootingService.class)
public class ShootingServiceTest {

    @InjectMocks
    private ShootingService shootingService;

    @Mock
    private Hero heroFunctions;

    @Mock
    private Villain villainFunctions;

    @Test
    void shouldCheckIfHealthOfHero(){
        Mockito.when(heroFunctions.getHealth()).thenReturn(100);
        String character = "hero";
        int heroHealth = shootingService.getCharacterHealth(character);
        assertThat(heroHealth,is(equalTo(100)));
        verify(heroFunctions).getHealth();
    }

    @Test
    void shouldCheckIfHealthOfVillain(){
        Mockito.when(villainFunctions.getHealth()).thenReturn(100);
        String character = "villain";
        int villainHealth = shootingService.getCharacterHealth(character);
        assertThat(villainHealth,is(equalTo(100)));
        verify(villainFunctions).getHealth();
    }

    @Test
    void shouldCheckIfOnHeroShootVillainHealthIsDecreased(){
        Mockito.when(villainFunctions.getHealth()).thenReturn(80);
        Mockito.doNothing().when(villainFunctions).reduceHealth();
        shootingService.characterAttack("hero");
        int villainReducedHealth = shootingService.getCharacterHealth("villain");
        verify(villainFunctions).reduceHealth();
        verify(villainFunctions).getHealth();
        assertThat(villainReducedHealth, is(equalTo(80)));
    }

    @Test
    void shouldCheckIfHeroHealthIsReducedOnVillainShoot(){
        Mockito.when(heroFunctions.getHealth()).thenReturn(80);
        Mockito.doNothing().when(heroFunctions).reduceHealth();
        shootingService.characterAttack("villain");
        int heroReducedHealth = shootingService.getCharacterHealth("hero");
        verify(heroFunctions).reduceHealth();
        verify(heroFunctions).getHealth();
        assertThat(heroReducedHealth, is(equalTo(80)));
    }

    @Test
    void toCheckIfVillainHealthIsReducedByTenWhenArmourIsOn(){
        Mockito.when(villainFunctions.getHealth()).thenReturn(90);
        Mockito.doNothing().when(villainFunctions).reduceHealth();
        Mockito.doNothing().when(villainFunctions).setArmourState(true);
        shootingService.reduceHealthOnArmourState(true);
        shootingService.characterAttack("hero");
        int reducedHealthOnActiveState = shootingService.getCharacterHealth("villain");
        verify(villainFunctions).getHealth();
        verify(villainFunctions).reduceHealth();
        verify(villainFunctions).setArmourState(true);
        assertThat(reducedHealthOnActiveState, is(equalTo(90)));
    }

    @Test
    void toSetBothHeroHealthAndVillainHealthToHundredOnRestart(){
        Mockito.when(villainFunctions.getHealth()).thenReturn(100);
        Mockito.when(heroFunctions.getHealth()).thenReturn(100);
        Mockito.doNothing().when(villainFunctions).setHealth(100);
        Mockito.doNothing().when(heroFunctions).setHealth(100);
        shootingService.restartServer();
        int heroHealthAfterRestart = shootingService.getCharacterHealth("hero");
        int villainHealthAfterRestart = shootingService.getCharacterHealth("villain");
        assertThat(heroHealthAfterRestart,is(equalTo(100)));
        assertThat(villainHealthAfterRestart,is(equalTo(100)));
        verify(villainFunctions).getHealth();
        verify(heroFunctions).getHealth();
        verify(villainFunctions).setHealth(100);
        verify(heroFunctions).setHealth(100);
        verify(villainFunctions).getHealth();
        verify(heroFunctions).getHealth();
    }
}
