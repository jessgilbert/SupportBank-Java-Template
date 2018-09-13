package training.supportbank;

import java.time.LocalDate;

public class Transaction {
    public String fromName;
    public String toName;
    public Double transAmount;
    public LocalDate transDate;

    public String toString() {
        return fromName + " " + toName + " " + transAmount + " " + transDate ;
    }

//    public Double getTransAmount(){
//
//    }
//

}
