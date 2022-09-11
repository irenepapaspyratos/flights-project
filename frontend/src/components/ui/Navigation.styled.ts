import styled, {css} from "styled-components";

const NavigationStyled = styled.nav<{ toggled: boolean }>`
    position: fixed;
    top: 1vmin;
    left: -54vmin;
    width: 50vmin;
    height: 75vh;
    background-color: #141B20AD;
    backdrop-filter: blur(15px);
    border-radius: 5px;
    border: 1px solid #646464;
    box-shadow: 10px 10px 15px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-end;
    padding-top: 2rem;
    gap: 1rem;
    font-size: larger;
    transform: ${({toggled}) =>
            toggled && css`translateX(55vmin);`};
    transition: transform 0.300s ease-in-out;
    z-index: 990;

    a {
        text-decoration: none;
        color: var(--color-primary-light);
    }
`;

export default NavigationStyled;
