import styled from "styled-components";

const HeaderStyled = styled.header`
    text-align: center;
    width: 100%;
    padding: 0.5vmin;
    position: sticky;
    top: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    color: var(--color-primary-light);

    h1 {
        text-shadow: var(--shadow-text);
    }
`;

export default HeaderStyled;
