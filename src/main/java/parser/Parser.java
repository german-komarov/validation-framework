package parser;

import rules.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class Parser {

    // "min: 10 | max : 40 | unique: employees,email"
    public static Set<Rule<Object>> parse(String validationString) throws ParsingException {
        if ( validationString == null ) {
            throw new ParsingException("Validation string cannot be null");
        } else if (! validationString.contains(":")) {
            throw new ParsingException("All rules must be separated by colon (:), but this string does not contain any colon, thus no rules provided");
        }

        Set<Rule<Object>> rules = new LinkedHashSet<>();
        String[] stringRules = validationString.split("\\|");

        for (String sr : stringRules) {
            sr = sr.trim();
            String[] s = sr.split(":");
            switch (s[0].trim()) {
                case "min": {
                    try {
                        rules.add(new MinRule(Integer.parseInt(s[1].trim())));
                    } catch (NumberFormatException e) {
                        throw new ParsingException("Non-integer value was provided for min rule");
                    }
                    break;
                }
                case "max": {
                    try {
                        rules.add(new MaxRule(Integer.parseInt(s[1].trim())));
                    } catch (NumberFormatException e) {
                        throw new ParsingException("Non-integer value was provided for max rule");
                    }
                    break;
                }
                case "unique": {
                    if (!s[1].contains(",")) {
                        throw new ParsingException("For unique rule two comma separated params should be provided");
                    }
                    // table - column
                    String[] tc = s[1].split(",");
                    rules.add(new UniqueRule(tc[0].trim(), tc[1].trim()));
                    break;
                }
                case "exists": {
                    if (!s[1].contains(",")) {
                        throw new ParsingException("For exists rule two comma separated params should be provided");
                    }
                    // table - column
                    String[] tc = s[1].split(",");
                    rules.add(new ExistsRule(tc[0].trim(), tc[1].trim()));
                    break;
                }
            }

        }

        return rules;
    }


}
