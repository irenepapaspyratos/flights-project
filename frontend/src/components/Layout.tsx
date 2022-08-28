import React from 'react';
import {HashRouter} from "react-router-dom";
import ContainerStyled from "./ui/Container.styled";
import Header from "./Header";
import AllRoutes from "./AllRoutes";
import {ToastContainer} from 'react-toastify';

export default function Layout() {
    return (
        <ContainerStyled>
            <HashRouter>
                <Header/>
                <AllRoutes/>
            </HashRouter>
            <ToastContainer/>
        </ContainerStyled>
    );
}
