package bankkata;

import java.time.LocalDate;
import java.util.function.Supplier;

@FunctionalInterface
public interface Today extends Supplier<LocalDate> {
    static Today create() {
        return LocalDate::now;
    }
}
