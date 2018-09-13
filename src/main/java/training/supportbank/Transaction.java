package training.supportbank;

import java.time.LocalDate;

public class Transaction {
    public String fromName;
    public String toName;
    public Double transAmount;
    public LocalDate transDate;

    public String toString() {
        return fromName + " owes " + toName + " for " + transAmount + " since " + transDate + "\n";
    }

//    public Double getTransAmount(){
//
//    }
//

}
