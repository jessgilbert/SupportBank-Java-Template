package training.supportbank;

import java.util.ArrayList;
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

    public String getSummary() {
        Double total = 0.0;

        for (Transaction currentTransaction : transactions) {

            if (name.equals(currentTransaction.fromAccount)) {
                total = total - currentTransaction.amount;
            }
            if(name.equals(currentTransaction.toAccount)) {
                total = total + currentTransaction.amount;
            }
        }

        if(total > 0 ) {
            return name + " is owed " + total + "\n:" ;
        }
        else if(total < 0 ) {
            return name + " owes " + total + "\n:";
        }
        else {
            return name + " isn't owed nor owes any amount" + "\n:";
        }


    }
}

