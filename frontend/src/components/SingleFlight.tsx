import React from "react";
import Flight from "./types/Flight";
import SingleFlightStyled from "./ui/SingleFlight.styled";

export default function SingleFlight(props: { flights: Flight[], index: number }) {
    const flight: Flight = props.flights[props.index];
    const stops: number = flight.itineraries[0].segments.length;

    return (
        <SingleFlightStyled>
            <div>
                <h3>
                    {flight.price.grandTotal + " " + flight.price.currency}
                </h3>
                <div>
                    Stops: {stops}
                </div>
                <div>
                    Available Seats: {flight.numberOfBookableSeats}
                </div>
            </div>
        </SingleFlightStyled>
    );
}
