import Airport from "./types/Airport";
import Airline from "./types/Airline";
import capitalizeFirstLetter from "../services/helpers/capitalize-firstLetter";
import {ChangeEvent, useState} from "react";
import DatePicker from "react-datepicker";

export default function FormElement(props: {
    variant: string, formElementType: string, formElementVariable?: Array<Airport | Airline>
}) {
    //variant = formId_variantIcon
    const ident: string = props.variant.replace('_', '-');
    const label: string = capitalizeFirstLetter(props.variant.split("_")[1]);


    const [value, setValue] = useState("")
    const [dateRange, setDateRange] = useState<[Date | null, Date | null]>([null, null]);
    const [startDate, endDate] = dateRange;


    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setValue(event.target.value);
    }


    return (
        <>
            {props.variant.includes("home") ?
                (
                    <>
                        {props.formElementType === "select" && (
                            <select>
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
                                    name={ident + "InputDatalist"}
                                    aria-label={label}
                                    onChange={handleChange}
                                />
                                <datalist id={ident + "Datalist"}>
                                    {props.formElementVariable?.filter(element =>
                                        element.iata.toLowerCase().startsWith(value)
                                        || element.locationServed.toLowerCase().startsWith(value)
                                    )
                                        .sort((a, b) => {
                                            return a.iata.localeCompare(b.iata);
                                        })
                                        .map((option, index) =>
                                            <option key={index}
                                                    value={option.iata}>
                                                {option.iata.replace(/\[[1-9]*]/, "") + " - " + option.name.replace(/\[[1-9]*]/, "")}
                                            </option>
                                        )}
                                </datalist>
                            </>
                        )}

                        {props.formElementType === "number" && (
                            <input type={"number"} min="1"/>
                        )}

                        {props.formElementType === "date" && (
                            <DatePicker
                                selectsRange={true}
                                startDate={startDate}
                                endDate={endDate}
                                onChange={update => {
                                    update && setDateRange(update);
                                }}
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
