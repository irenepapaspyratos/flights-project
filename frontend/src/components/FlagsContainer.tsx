import FlagsContainerStyled from "./ui/FlagsContainer.styled";
import React, {useState} from "react";
import {ButtonFlag} from "./ui/Button.styled";
import {useStore} from "./hooks/useStore";
import {useTranslation, UseTranslationResponse} from "react-i18next";

export default function FlagsContainer() {
    const translationHook: UseTranslationResponse<"translation"> = useTranslation();
    const setLanguage = useStore(state => state.setLanguage);
    const [active, setIsActive] = useState(localStorage.getItem("currentLanguage"));
    
    const handleLanguage = async (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        setIsActive(event.currentTarget.value);
        await setLanguage(event.currentTarget.value, translationHook);
    }

    return (
        <FlagsContainerStyled>
            <ButtonFlag type="button" variant={"en"} onClick={handleLanguage} value={"en"} choice={active}/>
            <ButtonFlag type="button" variant={"de"} onClick={handleLanguage} value={"de"} choice={active}/>
            <ButtonFlag type="button" variant={"es"} onClick={handleLanguage} value={"es"} choice={active}/>
        </FlagsContainerStyled>
    );
}
