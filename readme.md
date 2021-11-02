Packages : </br>

    * holder: Right now emulate DI container, supposed to be replaced by real Spring DI
    * parser: Contains Parser and its related exception. This class actually parse the validation string* into set of rules*
    * rules: Main abstact class is Rule. Also there is abstract SUBclass, which is (DatabaseAwareRule) which is abstract rule for unique and exists rules
    * validator: Validator is main class for validation. @Validatr annotation is used to pass validation string* there. And main exception is ValidationException

Validation string - similar to Laravel validation. Following options are already available : min, max, unique and exists </br>
Example of validation string : "min: 3 | max: 10 | unique: employees,email | exists: departments,name".</br>
Above validation string is of pattern "min: (mixSize) | max: (minSize) | unique: (table, column) | exists: (table, column)"
Notice : rules separated by vertical line (|) and value of each rule follows after a colon (:).</br>

SQL for creating mock tables and fill if with values (in already pre-created db) is located under resources/sql/test.sql.

Then you just run tests in test/java/MainTest and see the results and code examples
