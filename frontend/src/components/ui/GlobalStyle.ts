import {createGlobalStyle} from 'styled-components';

const GlobalStyle = createGlobalStyle`
    :root {
        --color-primary-dark: #001D29;
        --color-primary-light: #E9E9ED;
        --color-grey: #7D919A;
        --color-sea: #35BABA;
        --color-sun: #FFCA1C;
    }

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        height: 100vh;
        background-color: var(--color-primary-dark);
        color: var(--color-primary-dark);
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
        'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
        sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

    .react-datepicker-wrapper {
        text-align: justify;
    }

    .react-datepicker__close-icon::after {
        background-color: #630618;
        content: "X";
    }
`;

export default GlobalStyle;
