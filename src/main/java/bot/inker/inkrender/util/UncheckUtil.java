package bot.inker.inkrender.util;

import java.util.function.Function;
import java.util.function.Supplier;

public class UncheckUtil {
    private UncheckUtil() {
        throw new IllegalStateException();
    }

    public static <R> R uncheck(Throwable e) {
        return uncheckImpl(e);
    }

    private static <E extends Throwable, R> R uncheckImpl(Throwable e) throws E {
        throw (E) e;
    }

    public static <R> R uncheck(UncheckSupplierTask<R, ?> task) {
        return uncheckImpl(task);
    }

    private static <R, E extends Throwable> R uncheckImpl(UncheckSupplierTask<R, ?> task) throws E {
        return ((UncheckSupplierTask<R, E>) task).run();
    }

    public static <R> Supplier<R> cast(UncheckSupplierTask<R, ?> task) {
        return () -> uncheckImpl(task);
    }

    public static <T, R> Function<T, R> cast(UncheckFunctionTask<T, R, ?> task) {
        return t -> uncheckImpl(() -> task.run(t));
    }

    @FunctionalInterface
    public interface UncheckSupplierTask<R, E extends Throwable> {
        R run() throws E;
    }

    @FunctionalInterface
    public interface UncheckFunctionTask<T, R, E extends Throwable> {
        R run(T t) throws E;
    }
}
