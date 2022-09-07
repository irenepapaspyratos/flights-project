import create from "zustand";
import {UseTranslationResponse} from "react-i18next";

type ZustandState = {
    language: string,
    setLanguage: (value: string, translationHook: UseTranslationResponse<"translation">) => Promise<void>,
    getLanguage: () => void,
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
    }

}));
