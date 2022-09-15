import styled from "styled-components";

const SingleFlightStyled = styled.article`
    text-align: center;
    height: auto;
    width: 80%;
    max-width: 500%;
    margin: 8vmin auto;
    padding: 2vmin 2vmin;
    background-image: linear-gradient(#0f283d4a, #e9e9ed38);
    border-radius: 5px;
    backdrop-filter: blur(7px);
    box-shadow: 7px 7px 10px #0000005e;
    border: 1px solid #FFF0;
    color: var(--color-primary-light);
    font-family: 'Comfortaa', cursive;
    font-size: 1.25rem;
    font-weight: bolder;
    text-shadow: 2px 2px 3px black;

    input {
        color: var(--color-primary-dark);
        padding: 0 0 0 0.5rem;
    }
`;

export default SingleFlightStyled;
