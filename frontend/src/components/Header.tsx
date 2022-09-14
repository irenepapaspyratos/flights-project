import HeaderStyled from "./ui/Header.styled";
import FlagsContainer from "./FlagsContainer";
import React, {Dispatch, SetStateAction} from "react";
import Hamburger from "hamburger-react";

export default function Header(props: { toggled: boolean, toggle: Dispatch<SetStateAction<boolean>> }) {
    return (
        <HeaderStyled>
            <Hamburger toggled={props.toggled} toggle={props.toggle} size={24} distance={"sm"}
                       label="Show navigation"/>
            <FlagsContainer/>
            <h1>FLIGHTS</h1>
        </HeaderStyled>
    );
}
