package rules;

public abstract class Rule<T> {
    public abstract void apply(String fieldName, T value) throws RuleViolationException;
}
