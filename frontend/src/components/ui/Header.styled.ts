import styled, {css} from "styled-components";

const HeaderStyled = styled.header<{ path: string }>`
    text-align: center;
    width: 100%;
    padding: 0.5vmin 0.5vmin 2vmin 0.5vmin;
    position: sticky;
    top: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    color: var(--color-primary-light);
    background-attachment: fixed;
    z-index: 900;

    ${({path}) =>
            path === '/' && css`
                background-image: url("/images/BirdPlaneSearching.jpg");
                background-size: cover;
                background-position: center;`};

    ${({path}) =>
            path === '/flightlist' && css`
                background-image: url("/images/BirdBeachRelaxing.jpg");
                background-size: auto 300%;
                background-position: 50% 70%;
            `};


    h1 {
        text-shadow: var(--shadow-text);
    }
`;

export default HeaderStyled;
