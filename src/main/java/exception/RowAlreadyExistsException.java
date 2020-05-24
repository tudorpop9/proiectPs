package exception;

public class RowAlreadyExistsException extends Exception{
    public RowAlreadyExistsException(String message) {
        super(message);
    }
}
