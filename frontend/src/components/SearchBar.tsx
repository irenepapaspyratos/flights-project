import SearchBarStyled from "./ui/SearchBar.styled";
import FormElementWithIcon from "./FormElementWithIcon";

export default function SearchBar(props: { page: string }) {

    return (
        <>
            {props.page === "home" &&
                (<SearchBarStyled page={props.page}>
                    <form id={props.page}>
                        <FormElementWithIcon formId={props.page} variantIcon={"people"} formElementType={"select"}/>
                    </form>
                </SearchBarStyled>)}
        </>
    );
}
