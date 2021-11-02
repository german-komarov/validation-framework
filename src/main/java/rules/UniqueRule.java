package rules;

public class UniqueRule extends DatabaseAwareRule<Object> {



    public UniqueRule(String tableName, String columnName) {
        super(tableName, columnName);
    }

    @Override
    public void apply(String fieldName, Object value) throws RuleViolationException {
        boolean exists = super.exists(value);
        if (exists) {
            throw new RuleViolationException(fieldName, String.format("%s %s already exists",value, fieldName));
        }
    }


}
