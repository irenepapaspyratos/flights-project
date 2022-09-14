import React, {useState} from 'react';
import {useLocation} from "react-router-dom";
import ContainerStyled from "./ui/Container.styled";
import Header from "./Header";
import AllRoutes from "./AllRoutes";
import {ToastContainer} from 'react-toastify';
import Navigation from "./Navigation";
import Hamburger from "hamburger-react";

export default function Layout() {
    const location = useLocation();
    const [isOpen, setIsOpen] = useState(false)

    return (
        <ContainerStyled path={location.pathname}>
            <Hamburger toggled={isOpen} toggle={setIsOpen} size={24} distance={"sm"}/>
            <Header toggled={isOpen} toggle={setIsOpen}/>
            <AllRoutes/>
            <Navigation toggled={isOpen} toggle={setIsOpen}/>
            <ToastContainer/>
        </ContainerStyled>
    );
}
