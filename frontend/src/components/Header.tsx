import Navigation from "./Navigation";
import HeaderStyled from "./ui/Header.styled";

export default function Header() {
    return (
        <HeaderStyled>

            <h1>FLIGHTS</h1>
            <Navigation/>
        </HeaderStyled>
    );
}
