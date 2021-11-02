package validator;

public abstract class ValidationException extends Exception {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }


    public abstract String toJson();

}
