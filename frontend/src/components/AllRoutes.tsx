import {Route, Routes} from "react-router-dom";
import FlightList from "./pages/FlightList";
import Home from "./pages/Home";
import TheButton from "./pages/TheButton";

export default function AllRoutes() {
    return (
        <Routes>
            <Route path={"/"} element={<Home/>}/>
            <Route path={"/flightlist"} element={<FlightList/>}/>
            <Route path={"/the-button"} element={<TheButton/>}/>
        </Routes>
    );
}
