import styled, {css} from 'styled-components';

const Button = styled.button<{ variant: string }>`
    border-radius: 2.5px;
    background: none;

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
                font-weight: bold;
            `};
`;
export default Button;

export const ButtonFlag = styled.button<{ variant: string }>`
    height: 5vmin;
    width: 7.5vmin;
    margin: 1vmin;
    border-radius: 1px;
    border: 1px solid black;
    background-size: cover;
    background-position: center;
    background-image: url(${props => `/images/flag_${props.variant}.png`});

    :active {
        border-color: var(--color-primary-light);
    }

    :hover {
        box-shadow: 5px 5px 5px var(--color-primary-dark);
        cursor: pointer;
    }
`;
