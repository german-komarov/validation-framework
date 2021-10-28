import org.junit.Test;
import rules.MaxRule;
import rules.MinRule;
import validators.ValidationException;
import validators.Validator;

import java.io.File;


public class MainTest {

    class Foo {
        private int[] nums;

        public Foo() {
        }

        public Foo(int[] nums) {
            this.nums = nums;
        }

        public int[] getNums() {
            return nums;
        }

        public void setNums(int[] nums) {
            this.nums = nums;
        }
    }

    @Test
    public void testOnSuccess() throws NoSuchFieldException, ValidationException {
        Foo foo = new Foo(new int[] {5,6,1,3});
        Validator<Foo> validator = new Validator<>(foo);
        validator.addRule("nums", new MinRule(2));
        validator.addRule("nums", new MaxRule(6));
        validator.validate();
    }

    @Test(expected = ValidationException.class)
    public void testOnFailBecauseOfMax() throws NoSuchFieldException, ValidationException {
        Foo foo = new Foo(new int[] {4,7,1,5,7,3,7,2,1});
        Validator<Foo> validator = new Validator<>(foo);
        validator.addRule("nums", new MinRule(2));
        validator.addRule("nums", new MaxRule(6));
        validator.validate();
    }

    @Test(expected = ValidationException.class)
    public void testOnFailBecauseOfMin() throws NoSuchFieldException, ValidationException {
        Foo foo = new Foo(new int[] {3});
        Validator<Foo> validator = new Validator<>(foo);
        validator.addRule("nums", new MinRule(2));
        validator.addRule("nums", new MaxRule(6));
        validator.validate();
    }


}
