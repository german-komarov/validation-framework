import domain.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.ParsingException;
import validator.ValidationException;
import validator.Validator;


public class MainTest {

    @Test
    public void testOnFailBecauseOfMin() throws ValidationException, ParsingException {
        Employee foo = new Employee("test3@example.com","jo","IT");
        Validator<Employee> validator = new Validator<>(foo);
        boolean thrown = false;
        try {
            validator.validate();
        } catch (ValidationException e) {
            thrown = true;
            System.err.println("testOnFailBecauseOfMin() thrown message's json : "+e.toJson());
        }

        Assert.assertTrue(thrown);
    }


    @Test
    public void testOnFailBecauseOfMax() throws ParsingException {
        Employee foo = new Employee("test3@example.com","johnbenfoobar1234567890","IT");
        Validator<Employee> validator = new Validator<>(foo);
        boolean thrown = false;
        try {
            validator.validate();
        } catch (ValidationException e) {
            thrown = true;
            System.err.println("testOnFailBecauseOfMin() thrown message's json : "+e.toJson());
        }

        Assert.assertTrue(thrown);
    }

    @Test
    public void testOnFailBecauseOfNonExists() throws ParsingException {
        Employee foo = new Employee("test3@example.com","jo","RANDOM");
        Validator<Employee> validator = new Validator<>(foo);
        boolean thrown = false;
        try {
            validator.validate();
        } catch (ValidationException e) {
            thrown = true;
            System.err.println("testOnFailBecauseOfNonExists() thrown message's json : "+e.toJson());
        }

        Assert.assertTrue(thrown);
    }

    @Test
    public void testOnFailBecauseOfNonUnique() throws ParsingException {
        Employee foo = new Employee("test2@example.com","john","HR");
        Validator<Employee> validator = new Validator<>(foo);
        boolean thrown = false;
        try {
            validator.validate();
        } catch (ValidationException e) {
            thrown = true;
            System.err.println("testOnFailBecauseOfNonUnique() thrown message's json : "+e.toJson());
        }

        Assert.assertTrue(thrown);
    }

    @Test
    public void testOnSuccess() throws ParsingException {
        Employee foo = new Employee("test5@example.com","john","HR");
        Validator<Employee> validator = new Validator<>(foo);
        boolean thrown = false;
        try {
            validator.validate();
        } catch (ValidationException e) {
            thrown = true;
            System.err.println("testOnSuccess() thrown message's json : "+e.toJson());
        }
        Assert.assertFalse(thrown);
        System.err.println("testOnSuccess() did not throw exception");
    }


}
