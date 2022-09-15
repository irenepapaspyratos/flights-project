import HeaderStyled from "./ui/Header.styled";
import FlagsContainer from "./FlagsContainer";
import React from "react";

export default function Header(props: { path: string }) {
    return (
        <HeaderStyled path={props.path}>
            <FlagsContainer/>
            <h1>FLIGHTS</h1>
        </HeaderStyled>
    );
}
