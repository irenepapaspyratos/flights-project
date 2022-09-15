import React from "react";
import Flight from "./types/Flight";
import SingleFlightStyled from "./ui/SingleFlight.styled";

export default function SingleFlight(props: { flight: Flight }) {
    return (
        <SingleFlightStyled>
            <h2>{props.flight.price.grandTotal + " " + props.flight.price.currency}</h2>
            <div>
                Available Seats: {props.flight.numberOfBookableSeats}
            </div>
            <div>
                One Way: {props.flight.oneWay ? "True" : "False"}
            </div>
        </SingleFlightStyled>
    );
}
