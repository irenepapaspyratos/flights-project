import MainStyled from "../ui/Main.styled";
import Button from "../ui/Button.styled";
import {useEffect, useState} from "react";
import axios from "axios";
import Airport from "../types/Airport";

export default function TheButton() {
    const [show, setShow] = useState(false);
    const [airports, setAirports] = useState<Airport[]>([]);

    useEffect(() => {
        axios.get("/api/data/airports")
            .then(response => response.data)
            .then(data => setAirports(data))
    }, [])

    return (
        <MainStyled page={"thebutton"}>
            <Button type={"button"} onClick={() => setShow(!show)} variant={"Daniel"}>
                THE BUTTON
            </Button>
            <ul>
                {show && airports?.map(airport => {
                    // eslint-disable-next-line react/style-prop-object
                    // @ts-ignore
                    return <li key={airport.id}>{airport.iata} {airport.name}</li>
                })}
            </ul>
        </MainStyled>
    );
}
