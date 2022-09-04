import FlagsContainerStyled from "./ui/FlagsContainer.styled";
import React from "react";
import {UseTranslationResponse} from "react-i18next";
import {ButtonFlag} from "./ui/Button.styled";

export default function FlagsContainer(props: { translationHook: UseTranslationResponse<"translation"> }) {

    const handleLanguage = async (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        await props.translationHook.i18n.changeLanguage(event.currentTarget.value);
    }

    return (
        <FlagsContainerStyled>
            <ButtonFlag type="button" variant={"en"} onClick={handleLanguage} value={"en"}/>
            <ButtonFlag type="button" variant={"de"} onClick={handleLanguage} value={"de"}/>
            <ButtonFlag type="button" variant={"es"} onClick={handleLanguage} value={"es"}/>
        </FlagsContainerStyled>
    );
}
