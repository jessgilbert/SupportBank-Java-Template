package training.supportbank;

import java.time.LocalDate;

public class Transaction {
    public String fromName;
    public String toName;
    public Double transAmount;
    public LocalDate transDate;

    public String toString() {
        return transDate + " " + fromName + " owes " + toName + " £" + transAmount + "\n";
//        return fromName + " owes " + toName + " for " + transAmount + " since " + transDate + "\n";

    }

//    public Double getTransAmount(){
//
//    }
//

}
