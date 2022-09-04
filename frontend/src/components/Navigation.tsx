import {NavLink} from "react-router-dom";
import NavigationStyled from "./ui/Navigation.styled";
import {UseTranslationResponse} from "react-i18next";

export default function Navigation(props: { translationHook: UseTranslationResponse<"translation", undefined> }) {
    const t = props.translationHook.t;

    return (
        <NavigationStyled>
            <NavLink to={'/'}>{t('home.title')}</NavLink>
            <NavLink to={'/the-button'}>The BUTTON</NavLink>
        </NavigationStyled>
    )
}
