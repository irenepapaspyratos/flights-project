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
