package bankkata;

import java.util.function.Consumer;

@FunctionalInterface
public interface PrintLine extends Consumer<String> {
    static PrintLine create() {
        return System.out::println;
    }
}
