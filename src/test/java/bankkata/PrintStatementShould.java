package bankkata;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrintStatementShould {
    private static final List<Transaction> NO_TRAnSACTIONS = Collections.emptyList();

    private final List<String> printedLines = new ArrayList<>();
    private final PrintLine printLine = printedLines::add;

    private PrintStatement printStatement;

    @BeforeEach
    void setUp() {
        printedLines.clear();
        printStatement = PrintStatement.create(printLine);
    }

    @Test
    void always_print_a_header() {

        printStatement.accept(NO_TRAnSACTIONS);

        assertThat(printedLines).containsExactly("DATE | AMOUNT | BALANCE");
    }

    @Test
    void print_transactions_in_reverse_chronological_order() {

        List<Transaction> transactions = List.of(
                deposit("01/04/2014", 1000),
                withdrawal("02/04/2014", 100),
                deposit("10/04/2014", 500)
        );
        printStatement.accept(transactions);

         assertThat(printedLines).containsExactly(
                "DATE | AMOUNT | BALANCE",
                "10/04/2014 | 500.00 | 1400.00",
                "02/04/2014 | -100.00 | 900.00",
                "01/04/2014 | 1000.00 | 1000.00"
        );
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}