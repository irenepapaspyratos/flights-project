export default function capitalizeFirstLetter(wordLowerCase: string) {
    return wordLowerCase.charAt(0).toUpperCase() + wordLowerCase.slice(1);
}
