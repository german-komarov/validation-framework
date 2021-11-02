package rules;

import validator.ValidationException;

public class RuleViolationException extends ValidationException {

    private final String fieldName;
    private final String description;


    public RuleViolationException(String fieldName,String description) {
        this.fieldName = fieldName;
        this.description = description;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getMessage() {
        return this.description;
    }

    @Override
    public String toString() {
        return "RuleViolationException{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + description + '\'' +
                '}';
    }


    @Override
    public String toJson() {
        return "{" +
                "\"fieldName\":\"" + fieldName + "\"" +
                ", \"message\":\"" + description + "\"" +
                '}';
    }

}
