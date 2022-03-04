public enum ScrabbleErrorMessages
{
    NO_ERROR ("Great job!"),
    NOT_IN_DICTIONARY ("Word played is not in dictionary"),
    NOT_IN_TILES ("Letters are not in your tiles");

    private final String errorMessage;
    ScrabbleErrorMessages(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String toString()
    {
        return errorMessage;
    }
}
