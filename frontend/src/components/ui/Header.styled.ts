import styled from "styled-components";

const HeaderStyled = styled.header<{ path: string }>`
    text-align: center;
    width: 100%;
    padding: 0.5vmin 0.5vmin 5vmin 0.5vmin;
    position: sticky;
    top: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    color: var(--color-primary-light);
    z-index: 900;

    box-shadow: 6px 15px 20px var(--color-primary-dark);
    border-radius: 3px;
    background-image: linear-gradient(#3278a4 -10%, #090b1c);

    h1, h2 {
        text-shadow: var(--shadow-text);
    }

    h2 {
        font-size: 1rem;
    }
`;

export default HeaderStyled;
