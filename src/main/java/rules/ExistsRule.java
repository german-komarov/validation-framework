package rules;

public class ExistsRule extends DatabaseAwareRule<Object> {


    public ExistsRule(String tableName, String columnName) {
        super(tableName, columnName);
    }

    @Override
    public void apply(String fieldName, Object value) throws RuleViolationException {
        boolean exists = super.exists(value);
        if (!exists) {
            throw new RuleViolationException(fieldName, String.format("%s %s does not exist", value, fieldName));
        }
    }
}
