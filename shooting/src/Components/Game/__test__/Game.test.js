import { render, screen } from '@testing-library/react';
import Game from '../Game';
jest.mock("../../../__mocks__/axios");

test("Should check if Heading is present", () => {
    render(<Game/>);
    const headingElement = screen.getByRole("heading", {name : "Start Shooting!"});
    expect(headingElement).toBeVisible();
})

test("Should check if Hero Shoot button is present or not", ()=>{
    render(<Game/>);
    const buttonElement = screen.getAllByTestId("shootButtons");
    console.log(buttonElement);
    expect(buttonElement.length).toBe(3);
})

it("Should check if Hero's health is displayed" , async () => {
    render(<Game/>);
    const heroHealth = await screen.findByTestId("HealthOfHero");
    screen.debug();
    expect(heroHealth).toBeVisible;
})

test("Should check if Villains health is displayed", async () => {
    render(<Game/>);
    const villainHealth = await screen.findByTestId("HealthOfVillain");
    expect(villainHealth).toBeVisible();
})