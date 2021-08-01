package bankkata;

import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
public interface AddDeposit extends Consumer<Integer> {
    static AddDeposit create(TodayAsString todayAsString, AppendTransation appendTransation) {
        return amount -> appendTransation.accept(createDeposit(todayAsString, amount));
    }

    private static Transaction createDeposit(TodayAsString todayAsString, Integer amount) {
        return new Transaction(todayAsString.get(), amount);
    }
}
