package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ShootingController {

    @Autowired
    private ShootingService shootingService;

    @GetMapping("/health/{character}")
    public int health(@PathVariable String character){
        return shootingService.getCharacterHealth(character);
    }

    @PostMapping("/shoot")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public String characterShoot(@RequestParam String shooter){
        shootingService.characterAttack(shooter);
        return "Shoot Successful";
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/armour/{state}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public String armourState(@PathVariable boolean state){
        shootingService.reduceHealthOnArmourState(state);
        return "Armour State Changed";
    }

    @PostMapping("/restart")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String restartTheServer(){
        shootingService.restartServer();
        return "Restarted the Server";
    }
}
