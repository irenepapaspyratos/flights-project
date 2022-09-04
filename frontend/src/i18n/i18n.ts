import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';
import en from './locales/en/translations.json';
import de from './locales/de/translations.json';
import es from './locales/es/translations.json';


i18n
    .use(initReactI18next)
    .init({
        fallbackLng: 'en',
        resources: {
            en: {
                translations: en
            },
            de: {
                translations: de
            },

            es: {
                translations: es
            }
        },
        ns: ['translations'],
        defaultNS: 'translations',
        interpolation: {
            escapeValue: false
        }
    });

i18n.languages = ['en', 'de', 'es'];

export default i18n;
