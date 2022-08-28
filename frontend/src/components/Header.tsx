import Navigation from "./Navigation";
import HeaderStyled from "./ui/Header.styled";

export default function Header() {
    return (
        <HeaderStyled>
            <h1>Find your Flight</h1>
            <Navigation/>
        </HeaderStyled>
    );
}