package bankkata;

public class Account {
    private final AddDeposit addADeposit;
    private final AddWithdrawal addWithdrawal;
    private final AllTransactions allTransactions;
    private final PrintStatement printStatement;

    public Account(AddDeposit addADeposit,
                   AddWithdrawal addWithdrawal,
                   AllTransactions allTransactions,
                   PrintStatement printStatement) {
        this.addADeposit = addADeposit;
        this.addWithdrawal = addWithdrawal;
        this.allTransactions = allTransactions;
        this.printStatement = printStatement;
    }

    public void deposit(int amount) {
        addADeposit.accept(amount);
    }

    public void withdraw(int amount) {
        addWithdrawal.accept(amount);
    }

    public void printStatement() {
        printStatement.accept(allTransactions.get());
    }
}
