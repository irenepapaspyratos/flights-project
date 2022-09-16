import React from "react";
import Flight from "./types/Flight";
import SingleItemStyled from "./ui/SingleItem.styled";
import {useTranslation} from "react-i18next";

export default function SingleFlight(props: { flights: Flight[], index: number }) {
    const {t} = useTranslation();
    const flight: Flight = props.flights[props.index];
    const stops: number = flight.itineraries[0].segments.length;

    return (
        <SingleItemStyled>
            <div>
                <h3>
                    {flight.price.grandTotal + " " + flight.price.currency}
                </h3>
                <div>
                    Stops: {stops}
                </div>
                <div>
                    {t('flightlist.seats')}: {flight.numberOfBookableSeats}
                </div>
            </div>
        </SingleItemStyled>
    );
}
