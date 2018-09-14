package training.supportbank;

import java.time.LocalDate;

public class Transaction {
    public String fromAccount;
    public String toAccount;
    public Double amount;
    public LocalDate date;
    public String narrative;

    public String toString() {
        return date + " " + fromAccount + " owes " + toAccount + " £" + amount + " for " + narrative + "\n";
    }
}
