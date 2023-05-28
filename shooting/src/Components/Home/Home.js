import React from 'react'
import hero from '../../Images/hero.png'
import bad from "../../Images/bad.png"
import { Link } from 'react-router-dom'
import './HomeCss.css'



function Home() {
  return (
    <div className='container'>
        <div className='wrapper'>
            <div>
            <h1 className='shootingHeading'>Shooting</h1>
            </div>
            <div className='charracterImages'>
                <img src = {hero} alt='hero' id = "hero"></img>
                <img src = {bad} alt = 'villain' id = "villain"></img>
            </div> 
            <Link to = "/game" data-testid = "goToGame" id='gameButton'>Go to Game</Link>
        </div>
    </div>
  )
}

export default Home