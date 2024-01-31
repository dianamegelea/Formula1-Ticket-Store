package exceptions;

public class RaceDoesNotExistException extends F1StoreException {
    private static final String errorMessage =
            "A race with the name provided doesn't exist on the 2024 calendar";
    public RaceDoesNotExistException() {
        super(errorMessage);
    }
}
