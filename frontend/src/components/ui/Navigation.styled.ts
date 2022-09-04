import styled from "styled-components";

const NavigationStyled = styled.nav`
    display: flex;
    flex-direction: row;
    justify-content: center;
    gap: 2rem;

    a {
        text-decoration: none;
        color: var(--color-primary-light);
    }
`;

export default NavigationStyled;
