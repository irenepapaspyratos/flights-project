import React, {useEffect} from 'react';
import GlobalStyle from "./ui/GlobalStyle";
import Layout from "./Layout";
import {useStore} from "./hooks/useStore";

export default function App() {
    const airports = useStore(state => state.getData);
    const setAirports = useStore(state => state.setData);

    useEffect(() => {
        airports("airports").length === 0 && setAirports("airports")
    })

    return (
        <>
            <GlobalStyle/>
            <Layout/>
        </>
    );
}
