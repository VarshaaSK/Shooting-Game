import logo from './logo.svg';
import './App.css';
import Home from "./Components/Home/Home";
import Game from "./Components/Game/Game";
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path = '/' element = {<Home/>}></Route>
        <Route path = '/game' element = {<Game/>}></Route>
      </Routes>
    </div>
  );
}

export default App;
