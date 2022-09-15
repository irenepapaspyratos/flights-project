import SearchBar from "../SearchBar";
import MainStyled from "../ui/Main.styled";

export default function Home() {
    return (
        <MainStyled page={"home"}>
            <SearchBar page={"home"}/>
        </MainStyled>
    );
}
