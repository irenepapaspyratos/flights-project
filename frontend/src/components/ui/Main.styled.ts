import styled, {css} from "styled-components";

const MainStyled = styled.main<{ page: string }>`
    text-align: center;
    margin: 2vmin 0.5rem;
    display: flex;
    justify-content: center;
    ${({page}) =>
            (page === 'flightlist' || page === 'thebutton') && css`flex-direction: column;`};
    ${({page}) =>
            page === 'thebutton' && css`ul {
                color: var(--color-primary-light);
            }`};
`;

export default MainStyled;
