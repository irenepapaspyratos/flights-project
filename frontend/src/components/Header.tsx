import Navigation from "./Navigation";
import HeaderStyled from "./ui/Header.styled";
import FlagsContainer from "./FlagsContainer";
import React from "react";

export default function Header() {
    return (
        <HeaderStyled>
            <FlagsContainer/>
            <h1>FLIGHTS</h1>
            <Navigation/>
        </HeaderStyled>
    );
}
