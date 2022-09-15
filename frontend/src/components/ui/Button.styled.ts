import styled, {css} from 'styled-components';

const Button = styled.button<{ variant: string, onClick?: () => void }>`
    border-radius: 2.5px;
    background: none;
    text-align: center;

    :hover {
        box-shadow: 2px 2px 5px #282c34;
        cursor: pointer;
    }

    :active {
        border-color: aqua;
    }

    ${({variant}) =>
            variant === 'Daniel' &&
            css`
                border: 5px solid black;
                background: darkred;
                height: 3rem;
                width: 8rem;
                margin: 3rem auto;
                font-weight: bold;
            `};

    ${({variant}) =>
            variant === 'search' &&
            css`
                background-image: linear-gradient(#e89d02 20%, #352401);
                border-color: #e3bc6c #352401 #352401 #b5841f;
                height: 1.9rem;
                width: auto;
                min-width: 21vmin;
                padding: 0 2%;
                margin: auto 2vmin auto auto;
                font-weight: bold;
                color: var(--color-primary-light);
                text-shadow: 1px 1px 2px var(--color-primary-dark);
            `};

    ${({variant}) =>
            variant === 'flight' &&
            css`
                background-image: linear-gradient(#e89d02 20%, #352401);
                border-color: #e3bc6c #352401 #352401 #b5841f;
                height: 2.5rem;
                width: min-content;
                min-width: 21vmin;
                padding: 0 2%;
                margin: auto 2vmin auto auto;
                font-weight: bold;
                color: var(--color-primary-light);
                text-shadow: 1px 1px 2px var(--color-primary-dark);
            `};
`;
export default Button;

export const ButtonFlag = styled.button<{ variant: string, choice: string | null }>`
    height: 5vmin;
    width: 7.5vmin;
    margin: 1vmin;
    border-radius: 2px;
    border: 0.3vmin solid black;
    box-shadow: ${props => props.choice === props.variant ? "0 0 15px var(--color-primary-light)" : "none"};
    background-size: cover;
    background-position: center;
    background-image: url(${props => `/images/flag_${props.variant}.png`});

    :active {
        border-color: #009d9d;
        border-bottom-color: aqua;
        border-right-color: aqua;
    }

    :hover {
        box-shadow: ${props => props.choice === props.variant ? "2px 2px 5px var(--color-primary-light)" : "5px 5px 5px var(--color-primary-dark)"};
        cursor: pointer;
    }
`;
