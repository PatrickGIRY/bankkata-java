package bankkata;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@FunctionalInterface
public interface PrintStatement extends Consumer<List<Transaction>> {

    String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
    DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00", decimalFormatSymbols());

    private static DecimalFormatSymbols decimalFormatSymbols() {
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');
        return decimalFormatSymbols;
    }

    static PrintStatement create(PrintLine printLine) {

        return transactions -> {
            printLine.accept(STATEMENT_HEADER);
            printStatementLines(printLine, transactions);
        };
    }

    private static void printStatementLines(PrintLine printLine, List<Transaction> transactions) {
        final AtomicInteger newRunningBalance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> statementLine(transaction, newRunningBalance))
                .sorted(Comparator.reverseOrder())
                .forEach(printLine);
    }

    private static String statementLine(Transaction transaction,
                                        AtomicInteger newRunningBalance) {
        final var transactionAmount = transaction.amount();
        return transaction.date() +
                " | " +
                DECIMAL_FORMAT.format(transactionAmount) +
                " | " +
                DECIMAL_FORMAT.format(newRunningBalance.addAndGet(transactionAmount));
    }
}
