package bankkata;

import java.util.function.Consumer;

@FunctionalInterface
public interface AppendTransation extends Consumer<Transaction> {
}
