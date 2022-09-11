import styled from "styled-components";

const SearchBarStyled = styled.section<{ page: string }>`
    text-align: center;
    height: auto;
    max-width: 100%;
    margin: 0 2vmin;
    padding: 2vmin 2vmin;
    background-color: #E9E9ED24;
    border-radius: 5px;
    backdrop-filter: blur(5px);
    box-shadow: 0 4px 15px #00000033;
    border: 1px solid rgba(255, 255, 255, 0.16);
    color: var(--color-primary-light);

    input {
        color: var(--color-primary-dark);
    }
`;

export default SearchBarStyled;
