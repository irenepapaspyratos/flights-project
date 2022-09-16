import styled, {css} from "styled-components";

const MainStyled = styled.main<{ page: string }>`
    text-align: center;
    margin: 7vmin 0.5rem;
    display: flex;
    justify-content: center;
    ${({page}) =>
            (page === 'flightlist' || page === 'airports') && css`
                flex-direction: column;
                margin-top: 0;
            `};
    ${({page}) =>
            page === 'thebutton' && css`ul {
                color: var(--color-primary-light);
            }`};
`;

export default MainStyled;
