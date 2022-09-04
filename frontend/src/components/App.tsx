import React from 'react';
import GlobalStyle from "./ui/GlobalStyle";
import Layout from "./Layout";
import {useTranslation} from "react-i18next";

export default function App() {
    const translationHook = useTranslation();

    return (
        <>
            <GlobalStyle/>
            <Layout translationHook={translationHook}/>
        </>
    );
}
