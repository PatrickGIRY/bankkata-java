package feature;

import bankkata.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrintStatementFeature {

    private final List<String> printedLines = new ArrayList<>();
    private int dateIndex = 0;
    private static final String [] DATES = {"01/04/2014", "02/04/2014", "10/04/2014"};

    private Account account;

    @BeforeEach
    void setUp() {
        final var  memoryTransationsStore = new ArrayList<Transaction>();
        final TodayAsString  todayAsString = () -> DATES[dateIndex++];
        final var addADeposit = AddDeposit.create(todayAsString, memoryTransationsStore::add);
        final var addWithdrawal = AddWithdrawal.create(todayAsString, memoryTransationsStore::add);
        final var allTransactions = AllTransactions.create(memoryTransationsStore);
        final PrintLine printLine = printedLines::add;
        final var printStatement = PrintStatement.create(printLine);

        account = new Account(addADeposit, addWithdrawal, allTransactions, printStatement);
    }

    @Test
    void print_statement_containing_all_transactions() {
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        assertThat(printedLines).containsExactly(
                "DATE | AMOUNT | BALANCE",
                "10/04/2014 | 500.00 | 1400.00",
                "02/04/2014 | -100.00 | 900.00",
                "01/04/2014 | 1000.00 | 1000.00"
        );
    }
}
