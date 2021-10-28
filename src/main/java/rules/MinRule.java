package rules;

import java.lang.reflect.Array;

public class MinRule extends Rule<Object> {
    private final int size;

    public MinRule(int size) {
        this.size = size;
    }

    @Override
    public void apply(String fieldName, Object value) throws RuleViolationException {
        if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            if (length< size) {
                throw new RuleViolationException(fieldName, String.format("Min size is %d", size));
            }
        }
    }
}
