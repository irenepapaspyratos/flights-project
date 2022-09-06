import React from 'react';
import {useLocation} from "react-router-dom";
import ContainerStyled from "./ui/Container.styled";
import Header from "./Header";
import AllRoutes from "./AllRoutes";
import {ToastContainer} from 'react-toastify';

export default function Layout() {
    const location = useLocation();

    return (
        <ContainerStyled path={location.pathname}>
            <Header/>
            <AllRoutes/>
            <ToastContainer/>
        </ContainerStyled>
    );
}
