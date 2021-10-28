package rules;

import validators.ValidationException;

public class RuleViolationException extends ValidationException {

    private final String fieldName;
    private final String message;


    public RuleViolationException(String fieldName,String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toString() {
        return "RuleViolationException{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
