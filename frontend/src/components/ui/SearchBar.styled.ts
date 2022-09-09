import styled from "styled-components";

const SearchBarStyled = styled.section<{ page: string }>`
    text-align: center;
    height: auto;
    max-width: 75%;
    margin: 0 2vmin;
    padding: 1vmin;
    background-color: var(--color-primary-light);
`;

export default SearchBarStyled;
