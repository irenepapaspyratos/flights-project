import React from "react";
import FormElementWithIconStyled from "./ui/FormElementWithIcon.styled";
import IconSvg from "./ui/IconSvg";
import FormElement from "./FormElement";

export default function FormElementWithIcon(props: {
                                                name: string,
                                                formId: string,
                                                variantIcon: string,
                                                formElementType: string,
                                                formElementVariable?: any
                                            }
) {
    return (
        <FormElementWithIconStyled variantIcon={props.variantIcon}>
            <IconSvg variant={props.variantIcon} margin={"auto 2vmin"}/>
            <FormElement
                name={props.name}
                variant={props.formId + "_" + props.variantIcon}
                formElementType={props.formElementType}
                formElementVariable={props.formElementVariable}/>
        </FormElementWithIconStyled>
    );
}
