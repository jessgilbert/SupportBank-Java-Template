package training.supportbank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Person {
    public String name;
    public List<Transaction> transactions = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String toString() {
        return name + transactions + "\n";
    }

    public String getSummary(List<Transaction> transactions, HashMap<String, Person> hm) {
        Double total = 0.0;
        for (Transaction currentTransaction : transactions) {

            if (name.equals(currentTransaction.fromName)); {
                total - currentTransaction.transAmount;
            }
            else if (name.equals(currentTransaction.toName)) {
                total + currentTransaction.transAmount;
            }
        }
        if(total > 0 ) {
            return name + " is owed " + total + "\n:" ;
        }
        else if(total < 0 ) {
            return name + " owes " + total + "\n:";
        }
        else {
            return name + "isnt owed nor owes any amount" + "\n:";
        }


    }
}

