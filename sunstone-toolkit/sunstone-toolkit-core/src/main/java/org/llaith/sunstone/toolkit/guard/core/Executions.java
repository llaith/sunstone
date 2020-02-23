package org.llaith.sunstone.toolkit.guard.core;

import java.io.PrintStream;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import static org.llaith.sunstone.toolkit.guard.core.Guard.require;

/**
 *
 */
public class Executions {

    public static class RunResult {

        public static RunResult Success() {

            return new RunResult(null);

        }

        public static RunResult Failure(final Exception thrown) {

            return new RunResult(thrown);

        }

        private final Exception thrown;

        private RunResult(final Exception thrown) {

            this.thrown = thrown;

        }

        public void rethrow() {

            if (this.thrown != null) throw UncheckedException.wrap(this.thrown);

        }

        public RunResult ifSuccess(final Runnable runnable) {

            if (this.thrown == null) require(runnable).run();

            return this;

        }

        public RunResult ifException(final Consumer<Exception> consumer) {

            if (this.thrown != null) require(consumer).accept(this.thrown);

            return this;

        }

    }

    /* note, if you want to suppress the exception, wrap in a suppress call also */
    public static RunResult exec(final Runnable runnable) {

        try {

            require(runnable).run();

            return RunResult.Success();

        } catch (Exception e) {

            return RunResult.Failure(e);

        }

    }

    public static class CallResult<T> {

        public static <X> CallResult<X> Success(X result) {

            return new CallResult<>(result, null);

        }

        public static <X> CallResult<X> Failure(final Exception thrown) {

            return new CallResult<>(null, thrown);

        }

        private final T result;

        private final Exception thrown;

        private CallResult(final T result, final Exception thrown) {

            this.result = result;

            this.thrown = thrown;

        }

        public T returnOrRethrow() {

            if (this.thrown != null) throw UncheckedException.wrap(this.thrown);

            return this.result;

        }

        public Optional<T> optionalOrRethrow() {

            if (this.thrown != null) throw UncheckedException.wrap(this.thrown);

            return Optional.ofNullable(this.result);

        }

        public T returnOr(final Consumer<Exception> consumer) {

            if (this.thrown != null) require(consumer).accept(this.thrown);

            return this.result;

        }

        public CallResult<T> ifSuccess(final Consumer<T> consumer) {

            if (this.thrown == null) require(consumer).accept(this.result);

            return this;

        }

        public CallResult<T> ifException(final Consumer<Exception> consumer) {

            if (this.thrown != null) require(consumer).accept(this.thrown);

            return this;

        }

    }

    /* note, if you want to suppress the exception, wrap in a suppress call also */
    public static <X> CallResult<X> call(final Callable<X> callable) {


        try {

            return CallResult.Success(require(callable).call());

        } catch (Exception e) {

            return CallResult.Failure(e);

        }

    }

    private Executions() {}

    public static void main(String[] args) {

        final PrintStream out = System.out;

        exec(() -> out.println("Run 1")).rethrow();

        exec(() -> out.println("Run 2")).ifException(UncheckedException::wrap);

        out.println(call(() -> "Call 1").returnOrRethrow());

        out.println(call(() -> "Call 2").returnOr(UncheckedException::wrap));

        out.println(call(() -> "Call 3").optionalOrRethrow().map(String::toUpperCase));

    }

}
