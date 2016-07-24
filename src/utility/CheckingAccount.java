package utility;

/**
 * Created for Chapter 11 Exercise 03.
 */
public class CheckingAccount extends Account{
    private double overdraftLimit = 100.00;

    public CheckingAccount() {
    }

    public CheckingAccount(int id, double balance, double overdraftLimit) {
        super(id, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public int withdraw(double amount) {
        if (getBalance() - amount >= overdraftLimit) {
            setBalance(getBalance() - amount);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "id=" + getId() +
                ", balance=" + getBalance() +
                ", overdraftLimit=" + overdraftLimit +
                ", dateCreated=" + getDateCreated() +
                '}';
    }
}
