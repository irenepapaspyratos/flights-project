import styled from "styled-components";

const FormStyled = styled.form<{ id: string }>`
    max-width: 800px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
    width: 100%;
`;

export default FormStyled;
