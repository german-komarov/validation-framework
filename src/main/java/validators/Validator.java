package validators;

import rules.Rule;
import rules.RuleViolationException;

import java.lang.reflect.Field;
import java.util.*;

public class Validator<T>{
    private T object;
    private final Map<Field, List<Rule>> rulesByFields;

    public Validator(T object) {
        this.object = object;
        this.rulesByFields = new HashMap<>();
    }


    public void validate() throws ValidationException {
        for ( Field field : rulesByFields.keySet() ) {
            try {
                Object value = field.get(this.object);
                for ( Rule rule : rulesByFields.get(field) ) {
                    rule.apply(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (RuleViolationException e) {
                throw (ValidationException) e;
            }

        }
    }


    public void addRule(String fieldName, Rule rule) throws NoSuchFieldException {
        Field field = this.object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        List<Rule> rules = rulesByFields.get(field);
        if (rules==null) {
            rules = new ArrayList<>();
            rules.add(rule);
            rulesByFields.put(field, rules);
        } else {
            if (! rules.contains(rule)) {
                rules.add(rule);
            }
        }
    }





}
