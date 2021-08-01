package bankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AddDepositShould {

    private static final String TODAY = "12/05/2015";
    private static final int AMOUNT = 100;
    private final TodayAsString todayAsString = () -> TODAY;
    private final List<Transaction> memoryTransationsStore = new ArrayList<>();

    private  AddDeposit addDeposit;

    @BeforeEach
    void setUp() {
        addDeposit = AddDeposit.create(todayAsString, memoryTransationsStore::add);
    }

    @Test
    void create_and_store_a_deposit_transaction() {
        addDeposit.accept(AMOUNT);

        assertThat(memoryTransationsStore)
                .containsExactly(new Transaction(TODAY, AMOUNT));
    }
}