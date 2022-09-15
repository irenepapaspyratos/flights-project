import SearchBarStyled from "./ui/SearchBar.styled";
import FormElementWithIcon from "./FormElementWithIcon";
import {useStore} from "./hooks/useStore";
import "react-datepicker/dist/react-datepicker.css";
import FormStyled from "./ui/Form.styled";
import Button from "./ui/Button.styled";
import {useTranslation} from "react-i18next";
import {FormEvent} from "react";
import utcStringToYYYYmmDD from "../services/helpers/utcStringToYYYY-mm-DD";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function SearchBar(props: { page: string }) {
    const {t} = useTranslation();
    const navigate = useNavigate();
    const airports = useStore(state => state.getData("airports"));
    const formState = useStore(state => state.flightsFormState);
    const setOptions = useStore(state => state.setFlightOptions);

    function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const departureDateString = utcStringToYYYYmmDD(formState.dateDatepicker[0])
        const returnDateString = (formState.dateDatepicker[1] !== null ? "&returnDate=" + utcStringToYYYYmmDD(formState.dateDatepicker[1]) : "");

        return axios.get("/api/amadeus/flights?origin=" + formState.originDatalist +
            "&destination=" + formState.destinationDatalist +
            "&departDate=" + departureDateString +
            "&adults=" + formState.peopleNumber +
            returnDateString
        )
            .then(response => {
                console.log(response);
                return response.data;
            })
            .then(data => {
                setOptions(data);
                sessionStorage.setItem("origin", formState.originDatalist);
                sessionStorage.setItem("destination", formState.destinationDatalist);
                sessionStorage.setItem("oneWay", formState.dateDatepicker[1] !== null ? "no" : "yes")
            })
            .then(() => navigate("/flightlist"));

    }

    return (
        <>
            {props.page === "home" &&
                (<SearchBarStyled page={props.page}>
                    <FormStyled id={props.page} onSubmit={handleSubmit}>
                        <FormElementWithIcon name={"peopleNumber"} formId={props.page} variantIcon={"people"}
                                             formElementType={"number"} formElementVariable={airports}/>
                        <FormElementWithIcon name={"originDatalist"} formId={props.page} variantIcon={"origin"}
                                             formElementType={"inputDatalist"} formElementVariable={airports}/>
                        <FormElementWithIcon name={"destinationDatalist"} formId={props.page}
                                             variantIcon={"destination"}
                                             formElementType={"inputDatalist"} formElementVariable={airports}/>
                        <FormElementWithIcon name={"dateDatepicker"} formId={props.page} variantIcon={"date"}
                                             formElementType={"date"} formElementVariable={airports}/>
                        <Button type={"submit"} variant={"search"}>{t('home.searchButton')}</Button>
                    </FormStyled>
                </SearchBarStyled>)}
        </>
    );
}
