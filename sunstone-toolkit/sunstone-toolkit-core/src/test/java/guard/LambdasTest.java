package guard;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.llaith.sunstone.toolkit.guard.core.Lambdas.rethrowConsumer;
import static org.llaith.sunstone.toolkit.guard.core.Lambdas.rethrowFunction;
import static org.llaith.sunstone.toolkit.guard.core.Lambdas.rethrowSupplier;
import static org.llaith.sunstone.toolkit.guard.core.Lambdas.uncheck;

/**
 *
 */
public class LambdasTest {

    @Test
    public void test_Consumer_with_checked_exceptions() throws IllegalAccessException {
        Stream.of("java.lang.Object", "java.lang.Integer", "java.lang.String")
              .forEach(rethrowConsumer(className -> System.out.println(Class.forName(className))));

        Stream.of("java.lang.Object", "java.lang.Integer", "java.lang.String")
              .forEach(rethrowConsumer(System.out::println));
    }

    @Test
    public void test_Function_with_checked_exceptions() throws ClassNotFoundException {
        List<Class> classes1
                = Stream.of("Object", "Integer", "String")
                        .map(rethrowFunction(className -> Class.forName("java.lang." + className)))
                        .collect(Collectors.toList());

        List<Class> classes2
                = Stream.of("java.lang.Object", "java.lang.Integer", "java.lang.String")
                        .map(rethrowFunction(Class::forName))
                        .collect(Collectors.toList());
    }

    @Test
    public void test_Supplier_with_checked_exceptions() throws ClassNotFoundException {
        Collector.of(
                rethrowSupplier(() -> new StringJoiner(new String(new byte[] {77, 97, 114, 107}, "UTF-8"))),
                StringJoiner::add, StringJoiner::merge, StringJoiner::toString);
    }

    @Test
    public void test_uncheck_exception_thrown_by_method() {
        Class clazz1 = uncheck(() -> Class.forName("java.lang.String"));

        Class clazz2 = uncheck(Class::forName, "java.lang.String");
        
        
    }

    @Test(expected = ClassNotFoundException.class)
    public void test_if_correct_exception_is_still_thrown_by_method() {
        Class clazz3 = uncheck(Class::forName, "INVALID");
    }

}