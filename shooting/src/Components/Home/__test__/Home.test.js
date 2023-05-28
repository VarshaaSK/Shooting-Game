import { render, screen } from '@testing-library/react';
import Home from '../Home';
import { BrowserRouter } from 'react-router-dom';

const MockHome = () => {
    return(
        <BrowserRouter>
            <Home/>
        </BrowserRouter>
    )
}

test("Should check if Heading is present", ()=>{
    render(<MockHome/>);
    const headingElement = screen.getByRole("heading" , {name : "Shooting"});
    expect(headingElement).toBeVisible();
})

test("Should check if Hero image is present" , ()=>{
    render(<MockHome/>)
    const heroImage = screen.getByAltText("hero");
    expect(heroImage).toBeVisible();
})

test("Should check if Villain Image is present", ()=>{
    render(<MockHome/>)
    const villainImage = screen.getByAltText("villain");
    expect(villainImage).toBeVisible();
})

test("Should check if Link is present", ()=>{
    render(<MockHome/>);
    const linkElement = screen.getByTestId("goToGame");
    expect(linkElement).toBeVisible();
})



