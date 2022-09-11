import SearchBarStyled from "./ui/SearchBar.styled";
import FormElementWithIcon from "./FormElementWithIcon";
import {useStore} from "./hooks/useStore";
import "react-datepicker/dist/react-datepicker.css";
import FormStyled from "./ui/Form.styled";

export default function SearchBar(props: { page: string }) {
    const airports = useStore(state => state.getData("airports"));

    return (
        <>
            {props.page === "home" &&
                (<SearchBarStyled page={props.page}>
                    <FormStyled id={props.page}>
                        <FormElementWithIcon formId={props.page} variantIcon={"people"}
                                             formElementType={"number"} formElementVariable={airports}/>
                        <FormElementWithIcon formId={props.page} variantIcon={"origin"}
                                             formElementType={"inputDatalist"} formElementVariable={airports}/>
                        <FormElementWithIcon formId={props.page} variantIcon={"destination"}
                                             formElementType={"inputDatalist"} formElementVariable={airports}/>
                        <FormElementWithIcon formId={props.page} variantIcon={"date"}
                                             formElementType={"date"} formElementVariable={airports}/>
                    </FormStyled>
                </SearchBarStyled>)}
        </>
    );
}
