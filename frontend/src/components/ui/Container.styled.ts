import styled, {css} from 'styled-components';

const ContainerStyled = styled.div<{ path: string }>`
    position: relative;
    min-height: 100vh;
    background-size: cover;
    background-position: center;
    background-attachment: fixed;
    ${({path}) =>
            path === '/' &&
            css`
                background-image: url("/BirdPlaneSearching.jpg");
            `
    };
`;

export default ContainerStyled;
