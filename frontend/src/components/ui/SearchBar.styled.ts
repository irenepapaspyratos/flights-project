import styled from "styled-components";

const SearchBarStyled = styled.section<{ page: string }>`
    text-align: center;
    height: auto;
    max-width: 100%;
    margin: 0 2vmin;
    padding: 2vmin 2vmin;
    background-image: linear-gradient(transparent, #E9E9ED45);
    border-radius: 5px;
    backdrop-filter: blur(5px);
    box-shadow: 5px 5px 15px #0000005e;
    border: 1px solid #FFFFFF1A;
    color: var(--color-primary-light);

    input {
        color: var(--color-primary-dark);
        padding: 0 0 0 0.5rem;
    }
`;

export default SearchBarStyled;
