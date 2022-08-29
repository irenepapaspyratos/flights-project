import {Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import TheButton from "./pages/TheButton";


export default function AllRoutes() {
    return (
        <Routes>
            <Route path={"/"} element={<Home/>}/>
            <Route path={"/the-button"} element={<TheButton/>}/>
        </Routes>
    );
}
