import {useStore} from "../hooks/useStore";
import MainStyled from "../ui/Main.styled";
import SingleItemStyled from "../ui/SingleItem.styled";

export default function AirportList() {
    const airports = useStore(state => state.getData("airports"));

    return (
        <MainStyled page={"airports"}>
            <ul>
                {airports && airports.map(airport => {
                    return <SingleItemStyled key={airport.id}>{airport.iata} <br/> {airport.name}</SingleItemStyled>
                })}
            </ul>
        </MainStyled>
    );
}
