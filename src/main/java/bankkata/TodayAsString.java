package bankkata;

import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

@FunctionalInterface
public interface TodayAsString extends Supplier<String> {
    String DD_MM_YYYY = "dd/MM/yyyy";
    static TodayAsString create(Today today) {
        return () -> today
                .get()
                .format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
    }
}
