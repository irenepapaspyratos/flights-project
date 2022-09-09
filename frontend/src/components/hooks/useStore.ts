import create from "zustand";
import {UseTranslationResponse} from "react-i18next";
import getDbData from "../../services/db/get-dbData";
import Airport from "../types/Airport";
import Airline from "../types/Airline";

type ZustandState = {
    language: string,
    setLanguage: (value: string, translationHook: UseTranslationResponse<"translation">) => Promise<void>,
    getLanguage: () => void,
    airports: Airport[],
    setData: (identifier: string) => Promise<any> | undefined,
    getData: (identifier: string) => Array<Airport | Airline>,
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
    }

}));
