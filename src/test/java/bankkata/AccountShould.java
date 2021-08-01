package bankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountShould {
    private static final int EXPECTED_DEPOSIT_AMOUNT = 100;
    private static final int EXPECTED_WITHDRAWAL_AMOUNT = 200;
    private static final List<Transaction> GIVEN_TRANSACTIONS = List.of(new Transaction("05/12/2015", 124));

    private int depositAmount;
    private final AddDeposit addDeposit = amount -> depositAmount = amount;
    private int withdrawalAmount;
    private final AddWithdrawal addWithdrawal = amount -> withdrawalAmount = amount;
    private List<Transaction> transactionsToPrint = Collections.emptyList();
    private final AllTransactions allTransactions = () -> GIVEN_TRANSACTIONS;
    private final PrintStatement printStatement = transactions -> transactionsToPrint = transactions;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(addDeposit, addWithdrawal, allTransactions, printStatement);
    }

    @Test
    void store_a_deposit_transaction() {
        account.deposit(EXPECTED_DEPOSIT_AMOUNT);

        assertThat(depositAmount).isEqualTo(EXPECTED_DEPOSIT_AMOUNT);
    }

    @Test
    void store_a_withdrawal_transaction() {
        account.withdraw(EXPECTED_WITHDRAWAL_AMOUNT);

        assertThat(withdrawalAmount).isEqualTo(EXPECTED_WITHDRAWAL_AMOUNT);
    }

    @Test
    void print_a_statement() {
        account.printStatement();

        assertThat(transactionsToPrint).isEqualTo(GIVEN_TRANSACTIONS);
    }
}
