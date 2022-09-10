import {NavLink} from "react-router-dom";
import NavigationStyled from "./ui/Navigation.styled";
import {useTranslation} from "react-i18next";
import {Dispatch, SetStateAction} from "react";

export default function Navigation(props: { toggled: boolean, toggle: Dispatch<SetStateAction<boolean>> }) {
    const {t} = useTranslation();

    return (
        <NavigationStyled toggled={props.toggled}>
            <NavLink onClick={() => props.toggle(false)} to={'/'}>{t('home.title')}</NavLink>
            <NavLink onClick={() => props.toggle(false)} to={'/the-button'}>The BUTTON</NavLink>
        </NavigationStyled>
    )
}
