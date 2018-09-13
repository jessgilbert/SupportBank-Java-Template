package training.supportbank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    public String name;
    public List<Transaction> transactions = new ArrayList<>();

    public Person(String name){
        this.name = name;
    }

    public String toString() {
        return name + transactions + "\n";
    }

    public String getSummary(){
        Double total = 0
        for (Transaction currentTransaction: transactions) {

            if()
        }

    }
}
