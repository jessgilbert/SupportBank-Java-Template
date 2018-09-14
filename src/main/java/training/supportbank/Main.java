
package training.supportbank;
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

private static final Logger LOGGER = LogManager.getLogger();

public class Main {

    static Scanner userinput = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        // 1. Read the file
        List<String> lines = readTheFile();

        // 2. Create Transactions
        List<Transaction> transactions = createTransactions(lines);

        // 3. Create People
        HashMap<String, Person> people = createPeople(transactions);

        // 3.a) Add transactions to peoples' accounts
        createAccounts(transactions, people);

        // 4. Ask the user for their command
         giveCommand(transactions, people);
//         getSummary();

        // 5. Print out details

    }

    public static List<String> readTheFile() throws IOException {
        Path path = Paths.get("C:\\Users\\JJG\\Work\\Training\\SupportBank-Java-Template\\DodgyTransactions2015 (1).csv");
        List<String> lines = Files.readAllLines(path);
        return lines;

    }

    public static List<Transaction> createTransactions(List<String> lines) {
        ArrayList<Transaction> ts = new ArrayList<Transaction>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            String[] bits = line.split(",");

//            String narrative = bits[3];
            Transaction t = new Transaction();
            t.fromName = bits[1];
            t.transAmount = Double.parseDouble(bits[4]);
            t.toName = bits[2];
            t.transDate = LocalDate.parse(bits[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            t.narrative = bits[3];
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

