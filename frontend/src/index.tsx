import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './components/App';
import reportWebVitals from './reportWebVitals';
import {HashRouter} from "react-router-dom";
import './i18n/i18n'

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <HashRouter>
            <React.Suspense fallback={"loading..."}>
                <App/>
            </React.Suspense>
        </HashRouter>
    </React.StrictMode>
);

reportWebVitals();
