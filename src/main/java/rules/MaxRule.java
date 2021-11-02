package rules;


import java.lang.reflect.Array;

public class MaxRule extends Rule<Object> {
    private final int size;

    public MaxRule(int size) {
        this.size = size;
    }

    @Override
    public void apply(String fieldName, Object value) throws RuleViolationException {
        if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            if (length > size) {
                throw new RuleViolationException(fieldName, String.format("Max size is %d", size));
            }
        } else if (value.getClass().equals(String.class)) {
            if (((String)value).length() > size) {
                throw new RuleViolationException(fieldName, String.format("Max size is %d", size));
            }
        }
    }
}