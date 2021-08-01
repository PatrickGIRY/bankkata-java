package bankkata;

import java.util.List;
import java.util.function.Supplier;

@FunctionalInterface
public interface AllTransactions extends Supplier<List<Transaction>> {
    static AllTransactions create(List<Transaction> transactions) {
        return () -> List.copyOf(transactions);
    }
}
