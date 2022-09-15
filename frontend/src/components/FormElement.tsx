import Airport from "./types/Airport";
import Airline from "./types/Airline";
import capitalizeFirstLetter from "../services/helpers/capitalize-firstLetter";
import {useEffect, useState} from "react";
import DatePicker from "react-datepicker";
import {useStore} from "./hooks/useStore";

export default function FormElement(props: {
    name: string, variant: string, formElementType: string, formElementVariable?: Array<Airport | Airline>
}) {
    // DevNote: variant = formId_variantIcon, formId = pageName
    const ident: string = props.variant.replace('_', '-');
    const label: string = capitalizeFirstLetter(props.variant.split("_")[1]);

    const setFormState = useStore(state => state.setFlightsFormState);

    const [dateRange, setDateRange] = useState<[Date | null, Date | null]>([null, null]);
    const [startDate, endDate] = dateRange;

    const handleChange = (targetName: string, targetValue: string) => {
        setFormState({[targetName]: targetValue});
    }

    const handleDateUpdate = (update: [Date | null, Date | null]) => {
        update && setDateRange(update);
    }

    useEffect(() => {
        setFormState({dateDatepicker: dateRange});
    }, [dateRange])

    return (
        <>
            {props.variant.includes("home") ?
                (
                    <>
                        {props.formElementType === "select" && (
                            <select name={props.name}
                                    onChange={event => handleChange(event.target.name, event.target.value)}>
                                <option value={"default"}>Select...</option>
                                {props.formElementVariable?.map((option, index) =>
                                    <option key={index} value={option.id}>
                                        {option.name}
                                    </option>
                                )}
                            </select>
                        )}

                        {props.formElementType === "inputDatalist" && (
                            <>
                                <input
                                    list={ident + "Datalist"}
                                    id={ident + "InputDatalist"}
                                    name={props.name}
                                    aria-label={label}
                                    onChange={event => handleChange(event.target.name, event.target.value)}
                                />
                                <datalist id={ident + "Datalist"}>
                                    {props.formElementVariable?.map((option, index) =>
                                        <option key={index}
                                                value={option.iata}>
                                            {option.iata.replace(/\[[1-9]*]/, "") + " - " + option.name.replace(/\[[1-9]*]/, "")}
                                        </option>
                                    )}
                                </datalist>
                            </>
                        )}

                        {props.formElementType === "number" && (
                            <input name={props.name} type={"number"} min="1"
                                   onChange={event => handleChange(event.target.name, event.target.value)}/>
                        )}

                        {props.formElementType === "date" && (
                            <DatePicker
                                name={props.name}
                                selectsRange={true}
                                startDate={startDate}
                                endDate={endDate}
                                onChange={update => handleDateUpdate(update)}
                                isClearable={true}
                            />
                        )}
                    </>
                )
                :
                (
                    <>
                        You are trying to create a Non-Homepage component !!
                    </>
                )
            }
        </>
    );
}
