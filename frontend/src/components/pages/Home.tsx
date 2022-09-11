import SearchBar from "../SearchBar";
import MainStyled from "../ui/Main.styled";

export default function Home() {
    return (
        <MainStyled>
            <SearchBar page={"home"}/>
        </MainStyled>
    );
}
