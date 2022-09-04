import Navigation from "./Navigation";
import HeaderStyled from "./ui/Header.styled";
import {UseTranslationResponse} from "react-i18next";
import FlagsContainer from "./FlagsContainer";
import React from "react";

export default function Header(props: { translationHook: UseTranslationResponse<"translation"> }) {
    return (
        <HeaderStyled>
            <FlagsContainer translationHook={props.translationHook}/>
            <h1>FLIGHTS</h1>
            <Navigation translationHook={props.translationHook}/>
        </HeaderStyled>
    );
}
