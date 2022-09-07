import {NavLink} from "react-router-dom";
import NavigationStyled from "./ui/Navigation.styled";
import {useTranslation} from "react-i18next";

export default function Navigation() {
    const {t} = useTranslation();

    return (
        <NavigationStyled>
            <NavLink to={'/'}>{t('home.title')}</NavLink>
            <NavLink to={'/the-button'}>The BUTTON</NavLink>
        </NavigationStyled>
    )
}
