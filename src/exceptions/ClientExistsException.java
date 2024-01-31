package exceptions;

public class ClientExistsException extends F1StoreException {
    private static final String errorMessage =
            "A customer with the name %s already exists in our system.";
    public ClientExistsException(String customerName) {
        super(String.format(errorMessage, customerName));
    }
}
