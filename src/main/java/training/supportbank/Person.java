package training.supportbank;

import java.time.LocalDate;
import java.util.List;

public class Person {
    public String name;
    public List<Transaction> transactions;

    public Person(String name){
        this.name = name;
    }
}
