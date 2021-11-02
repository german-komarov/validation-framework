package validator;

import parser.Parser;
import parser.ParsingException;
import rules.Rule;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Validator<T>{
    private final T object;
    private final Map<Field, Set<Rule<Object>>> rulesByFields;

    public Validator(T object) throws ParsingException {
        this.object = object;
        this.rulesByFields = new HashMap<>();
        for (Field f : object.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Validate v = f.getAnnotation(Validate.class);
            if (v==null) {
                continue;
            }
            this.rulesByFields.put(f, Parser.parse(v.rules()));
        }
    }


    public void validate() throws ValidationException {
        for ( Field field : rulesByFields.keySet() ) {
            try {
                Object value = field.get(this.object);
                for ( Rule<Object> rule : rulesByFields.get(field) ) {
                    rule.apply(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }


    public void addRule(String fieldName, Rule<Object> rule) throws NoSuchFieldException {
        Field field = this.object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Set<Rule<Object>> rules = rulesByFields.get(field);
        if (rules==null) {
            rules = new LinkedHashSet<>();
            rules.add(rule);
            rulesByFields.put(field, rules);
        } else {
            rules.add(rule);
        }
    }





}
