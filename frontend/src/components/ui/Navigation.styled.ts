import styled, {css} from "styled-components";

const NavigationStyled = styled.nav<{ toggled: boolean }>`
    position: fixed;
    top: 1vmin;
    left: -58vmin;
    width: max-content;
    height: 45vh;
    background-color: #001D295E;
    backdrop-filter: blur(20px);
    border-radius: 5px;
    border: 1px solid #646464;
    box-shadow: 10px 10px 15px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-end;
    padding: 3rem 1rem 2rem 2rem;
    gap: 1rem;
    font-size: larger;
    text-shadow: var(--shadow-text);
    transform: ${({toggled}) =>
            toggled && css`translateX(59vmin);`};
    transition: transform 0.300s ease-in-out;
    z-index: 990;

    a {
        text-decoration: none;
        color: var(--color-primary-light);
    }
`;

export default NavigationStyled;
