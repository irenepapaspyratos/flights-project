import HeaderStyled from "./ui/Header.styled";
import FlagsContainer from "./FlagsContainer";
import React from "react";

export default function Header(props: { path: string }) {
    const oneWay = sessionStorage.getItem("oneWay") === "yes" ? <> &#8658; </> : <> &#8660; </>;

    return (
        <HeaderStyled path={props.path}>
            <FlagsContainer/>
            <h1>FLIGHTS</h1>
            <h2>
                {props.path === "/flightlist" ?
                    <>
                        {sessionStorage.getItem("origin")}
                        {oneWay}
                        {sessionStorage.getItem("destination")}
                    </> :
                    <br/>
                }
            </h2>
        </HeaderStyled>
    );
}
