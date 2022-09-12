import styled, {css} from "styled-components";

const FormElementWithIconStyled = styled.div<{ variantIcon: string }>`
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;

    ${({variantIcon}) =>
            variantIcon === 'date' ? css`width: 70%;` : css`width: 32%;`};

    svg {
        width: 10%
    }

    input {
        ${({variantIcon}) =>
                variantIcon === 'date' ? css`width: 90%;` : css`width: 85%;`};
        line-height: 2;
        margin: 1vmin 2vmin;
        background-color: var(--color-primary-light);
    }
`;

export default FormElementWithIconStyled;
