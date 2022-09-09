import React from "react";
import FormElementWithIconStyled from "./ui/FormElementWithIcon.styled";
import IconSvg from "./ui/IconSvg";
import FormElement from "./FormElement";

export default function FormElementWithIcon(props: {
                                                formId: string,
                                                variantIcon: string,
                                                formElementType: string,
                                                formElementVariable?: any
                                            }
) {
    return (
        <FormElementWithIconStyled>
            <IconSvg variant={props.variantIcon}/>
            <FormElement
                variant={props.formId + "_" + props.variantIcon}
                formElementType={props.formElementType}
                formElementVariable={props.formElementVariable}/>
        </FormElementWithIconStyled>
    );
}
