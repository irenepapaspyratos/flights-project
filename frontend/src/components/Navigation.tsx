import {NavLink} from "react-router-dom";
import NavigationStyled from "./ui/Navigation.styled";

export default function Navigation() {
    return (
        <NavigationStyled>
            <NavLink to={'/'}>Home</NavLink>
        </NavigationStyled>
    )
}
