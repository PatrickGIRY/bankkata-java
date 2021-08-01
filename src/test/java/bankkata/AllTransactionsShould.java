package bankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AllTransactionsShould {

    private static final Transaction TRANSACTION_1 = new Transaction("05/12/2015", 124);
    private static final Transaction TRANSACTION_2 = new Transaction("26/11/2015", 500);
    private static final List<Transaction> GIVEN_TRANSACTIONS = new ArrayList<>();

    private AllTransactions allTransactions;

    @BeforeEach
    void setUp() {
        GIVEN_TRANSACTIONS.add(TRANSACTION_1);
        GIVEN_TRANSACTIONS.add(TRANSACTION_2);
        allTransactions = AllTransactions.create(GIVEN_TRANSACTIONS);
    }

    @Test
    void return_all_transactions() {
        final var transactions = allTransactions.get();

        assertThat(transactions).containsExactlyElementsOf(GIVEN_TRANSACTIONS);
    }

    @Test
    void not_return_a_mutable_transaction_list() {
        final var transactions = allTransactions.get();

        assertThatThrownBy(() -> transactions.add(TRANSACTION_1))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}