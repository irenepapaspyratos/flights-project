import {useStore} from "../hooks/useStore";
import Flight from "../types/Flight";
import SingleFlight from "../SingleFlight";
import MainStyled from "../ui/Main.styled";

export default function FlightList() {
    const optionsState = useStore(state => state.flightOptions);

    const options = optionsState.map((flight: Flight, index) =>
        <SingleFlight key={flight.id} index={index} flights={optionsState}/>
    );

    return (
        <MainStyled page={"flightlist"}>
            <form>
                {optionsState.length > 0 &&
                    <>{options}</>
                }
            </form>
        </MainStyled>
    );
}
