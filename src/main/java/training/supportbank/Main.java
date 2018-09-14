
package training.supportbank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    static Scanner userinput = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        LOGGER.debug("-----");
        LOGGER.debug("Reading the file");
        // 1. Read the file
        List<String> lines = readTheFile();

        LOGGER.info("Creating transactions");
        List<Transaction> transactions = createTransactions(lines);

        LOGGER.info("Creating People");
        HashMap<String, Person> people = createPeople(transactions);

        LOGGER.info("Creating Accounts");
        createAccounts(transactions, people);

        LOGGER.info("Asking for command");
         giveCommand(transactions, people);


    }

    public static List<String> readTheFile() throws IOException {
        Path path = Paths.get("C:\\Users\\JJG\\Work\\Training\\SupportBank-Java-Template\\2DodgyTransactions2015.csv");
        List<String> lines = Files.readAllLines(path);
        return lines;

    }

    public static List<Transaction> createTransactions(List<String> lines) {
        ArrayList<Transaction> ts = new ArrayList<Transaction>();

        for (int i = 1; i < lines.size(); i++) {
            LOGGER.info("About to create transactions for line number " + i);

            String line = lines.get(i);
            String[] bits = line.split(",");

            Transaction t = new Transaction();
            t.fromName = bits[1];
            t.toName = bits[2];
            t.narrative = bits[3];

            try {
                t.transAmount = Double.parseDouble(bits[4]);
            } catch (Exception e) {
                LOGGER.error(" I am trying to record the transaction amounts and am expecting numbers only. There was an error on line " + i + ". Error was with: " + bits[4] + ". Please change this to the correct format of a decimal number.");

            }

            try {
                t.transDate = LocalDate.parse(bits[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                LOGGER.error("I am trying to read the date of this transaction. On line " + i + " I am expecting it in the format \"dd/MM/yyyy\". If \"" + bits[0] + "\" doesn't look in that format, please change it to this.");
            }
            ts.add(t);
        }

        return ts;
    }

    public static HashMap<String, Person> createPeople(List<Transaction> transactions) {
        HashMap<String, Person> hm = new HashMap<>();

        for (int i = 1; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);


            if (!hm.containsKey(transaction.fromName)) {
                Person p = new Person(transaction.fromName);
                hm.put(transaction.fromName, p);
            }
            if (!hm.containsKey(transaction.toName)) {
                Person p = new Person(transaction.toName);
                hm.put(transaction.toName, p);
            }
        }
        return hm;
    }

    public static void createAccounts(List<Transaction> transactions, HashMap<String, Person> hm) {

        for (int i = 1; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);

            String from = transaction.fromName;
            Person fromPerson = hm.get(from);
            fromPerson.transactions.add(transaction);

            String to = transaction.toName;
            Person toPerson = hm.get(to);
            toPerson.transactions.add(transaction);
        }

    }

    public static void giveCommand(List<Transaction> transactions, HashMap<String, Person> hm) {

        System.out.print("Would you like to \"List all\" total transactions or \"List Account name\" ");

//        Person people = new Person();

//        hm.get("Todd").getSummary(List<Transaction> transactions, );

        if (userinput.hasNextLine()) {
            String lineOfText = userinput.nextLine();

            if (lineOfText.equals("List all")) {
                for (Person person: hm.values()) {
                    System.out.println(person.getSummary());
                }


            }

            else if (lineOfText.startsWith("List") ) {
                String name = lineOfText.substring(5);
                System.out.print(hm.get(name));

                /* For total transaction amounts
                 if name typed in is equal to the fromName then - transAmount
                 if name typed in is equal to toName then + transAmount for each transaction
                */
            }
        }


    }
}


// if(userinput.equals(Person))
//            System.out.println(Person.transactions);
//    public static Hashmap<String, Person> createPerson(List<Transaction>) {
//
//
//    }
//
// System.out.println("There are " + people.size() + " people");
//        for (Person p : people) {
//            System.out.println(p.name);
//
//            if (p.name.equals(p));
//
//        }

//    private void setName(String nextLine) {
//    }

