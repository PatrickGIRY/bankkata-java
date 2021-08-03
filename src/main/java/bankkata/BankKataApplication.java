package bankkata;

import java.util.ArrayList;

public interface BankKataApplication {

    static void main(String[] args) {
        final var memoryTransationsStore = new ArrayList<Transaction>();
        final var todayAsString = TodayAsString.create(Today.create());
        final var addADeposit = AddDeposit.create(todayAsString, memoryTransationsStore::add);
        final var addWithdrawal = AddWithdrawal.create(todayAsString, memoryTransationsStore::add);
        final var allTransactions = AllTransactions.create(memoryTransationsStore);
        final var printLine = PrintLine.create();
        final var printStatement = PrintStatement.create(printLine);

        final var account = new Account(addADeposit, addWithdrawal, allTransactions, printStatement);

        account.deposit(1000);
        account.withdraw(400);
        account.deposit(100);

        account.printStatement();
    }
}
