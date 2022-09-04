import React from 'react';
import {useLocation} from "react-router-dom";
import ContainerStyled from "./ui/Container.styled";
import Header from "./Header";
import AllRoutes from "./AllRoutes";
import {ToastContainer} from 'react-toastify';
import {UseTranslationResponse} from "react-i18next";

export default function Layout(props: { translationHook: UseTranslationResponse<"translation"> }) {
    const location = useLocation();

    return (
        <ContainerStyled path={location.pathname}>
            <Header translationHook={props.translationHook}/>
            <AllRoutes/>
            <ToastContainer/>
        </ContainerStyled>
    );
}
