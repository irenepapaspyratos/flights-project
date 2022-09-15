import {useStore} from "../hooks/useStore";
import Flight from "../types/Flight";
import SingleFlight from "../SingleFlight";
import MainStyled from "../ui/Main.styled";

export default function FlightList() {
    const optionsState = useStore(state => state.flightOptions);

    const options = optionsState.map((flight: Flight) =>
        <SingleFlight key={flight.id} flight={flight}/>
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
