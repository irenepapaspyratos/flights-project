import styled, {css} from 'styled-components';

const ContainerStyled = styled.div<{ path: string }>`
    position: relative;
    min-height: 100vh;
    background-attachment: fixed;

    ${({path}) =>
            path === '/' &&
            css`
                background-image: url("/images/BirdPlaneSearching.jpg");
                background-size: cover;
                background-position: center;
            `
    };
    ${({path}) =>
            path === '/flightlist' &&
            css`
                background-image: url("/images/BirdBeachRelaxing.jpg");
                background-size: auto 300%;
                background-position: 50% 70%;
            `
    };
`;

export default ContainerStyled;
