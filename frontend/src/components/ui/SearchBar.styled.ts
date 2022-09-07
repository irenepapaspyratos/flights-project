import styled from "styled-components";

const SearchBarStyled = styled.section<{ page: string }>`
    text-align: center;
    height: 3rem;
    max-width: 75%;
    margin: 0 2vmin;
    background-color: var(--color-primary-light);
`;

export default SearchBarStyled;
