package utility;

/**
 * Created for Chapter 11 Exercise 03.
 */
public class SavingsAccount extends Account {

    public SavingsAccount() {
    }

    public SavingsAccount(int id, double balance) {
        super(id, balance);
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "id=" + getId() +
                ", balance=" + getBalance() +
                ", dateCreated=" + getDateCreated() +
                '}';
    }
}
