import create from "zustand";
import {UseTranslationResponse} from "react-i18next";
import getDbData from "../../services/db/get-dbData";
import Airport from "../types/Airport";
import Airline from "../types/Airline";
import Flight from "../types/Flight";

type ZustandState = {
    language: string,
    setLanguage: (value: string, translationHook: UseTranslationResponse<"translation">) => Promise<void>,
    getLanguage: () => void,
    airports: Airport[],
    setData: (identifier: string) => Promise<any> | undefined,
    getData: (identifier: string) => Array<Airport | Airline>,
    flightsFormState: { peopleNumber: string; originDatalist: string; destinationDatalist: string; dateDatepicker: string; },
    setFlightsFormState: (args: {}) => void,
    flightOptions: Flight[],
    setFlightOptions: (flights: Flight[]) => void,
}

export const useStore = create<ZustandState>((set) => ({

    language: "en",
    setLanguage: async (value, translationHook) => {
        await translationHook.i18n.changeLanguage(value);
        set({language: value});
        localStorage.setItem("currentLanguage", value);
    },
    getLanguage: () => {
        localStorage.getItem("currentLanguage");
    },

    airports: [],
    airlines: [],
    setData: async (identifier: string) => {
        const resultList = await getDbData(identifier);
        set({[identifier]: resultList});
        localStorage.setItem(identifier, JSON.stringify(resultList));
    },
    getData: (identifier: string) => {
        const storageString = localStorage.getItem(identifier);
        return JSON.parse(storageString ? storageString : "[]");
    },

    flightsFormState: {peopleNumber: "", originDatalist: "", destinationDatalist: "", dateDatepicker: ""},
    setFlightsFormState: (args: {}) => {
        let result = {};
        const list: [string, string][] = Object.entries(args);
        for (let [key, value] of list) {
            result = {...result, [key]: value}
        }
        set(state => {
            return {flightsFormState: {...state.flightsFormState, ...result}};
        });
    },

    flightOptions: [],
    setFlightOptions: (flights: Flight[]) => {
        set({flightOptions: flights});
    },
}));
