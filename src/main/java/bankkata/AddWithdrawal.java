package bankkata;

import java.util.function.Consumer;

@FunctionalInterface
public interface AddWithdrawal extends Consumer<Integer> {
    static AddWithdrawal create(TodayAsString todayAsString, AppendTransation appendTransation) {
        return amount -> {  appendTransation.accept(withdrawal(todayAsString, amount)); };
    }

   private static Transaction withdrawal(TodayAsString todayAsString, Integer amount) {
        return new Transaction(todayAsString.get(), -amount);
    }
}
