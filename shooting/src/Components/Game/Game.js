import React, { useEffect, useState } from 'react'
import axios from "axios";

function Game() {

    const[heroHealth, setHeroHealth] = useState(0);
    const characterHero = "hero"

    const[villainHealth, setVillainHealth] = useState(0);
    const characterVillain = "villain"

    const[armourState, setArmourState] = useState(false);

    const fetchHeroHealth = async () => {

        await axios.get("http://localhost:8080/api/health/hero")
        .then((response)=>{
            console.log(response);
            console.log(response.data);
            setHeroHealth(response.data);
        });
    }

    const fetchVillainHealth = async () => {
        await axios.get("http://localhost:8080/api/health/villain")
        .then((response) =>{
            console.log(response.data);
            setVillainHealth(response.data);
        });
    }

    const changeVillainHealth = async () => {
        await axios.post("http://localhost:8080/api/shoot?shooter=hero")
        .then((response) => {
            console.log(response);
        })
    }

    const changeHeroHealth = async () => {
        var shooter = "villain";
        await axios.post("http://localhost:8080/api/shoot", {},{params:{
            shooter:shooter
        }})
        .then((response) => {
            console.log(response);
        })
    }

    const fetchArmourState = async () => {
        var state = armourState;
        await axios.post("http://localhost:8080/api/armour/" + state)
        .then((response) => {
            console.log(response);
        })
        setArmourState(!armourState);   
        console.log(armourState);
    }

    const fetchRestartApi = async () => {
        await axios.post("http://localhost:8080/api/restart")
        .then((response) => {
            console.log(response);
        })
    }

    useEffect(()=>{
        fetchHeroHealth();
        fetchVillainHealth();
    },[])

    const heroShoot = () => {
        changeVillainHealth();
        fetchVillainHealth();
        console.log(villainHealth);
    }

    const villainShoot = () => {
        changeHeroHealth();
        fetchHeroHealth();
        console.log(heroHealth);
    }

    const changeArmourState = () => {
        fetchArmourState();
        console.log(armourState);
    }

    const backToNormal = () => {
        fetchRestartApi();
        fetchHeroHealth();
        fetchVillainHealth();
    }


  return (
    <div>
        <h1>Start Shooting!</h1>
        <button data-testid="shootButtons" onClick = {heroShoot}>Hero Shoot</button>
        <h3 data-testid = "HealthOfHero">{heroHealth<=0?"Game Over! Villain WINS👾":heroHealth}</h3>
        <button data-testid = "shootButtons" onClick={villainShoot}>Villain Shoot</button>
        <h3 data-testid = "HealthOfVillain">{villainHealth<=0?"Game Over! Hero WINS🦸‍♀️":villainHealth}</h3>
        <button data-testid = "shootButtons" onClick={changeArmourState}>Armour</button>
        <button onClick={backToNormal}>Restart</button>
    </div>
  )
}

export default Game