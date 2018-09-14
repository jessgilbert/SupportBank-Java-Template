package training.supportbank;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    static Scanner userinput = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String filename = "C:\\Users\\JJG\\Work\\Training\\SupportBank-Java-Template\\2Transactions2014.csv";
//        String filename = "C:\\Users\\JJG\\Work\\Training\\SupportBank-Java-Template\\Transactions2013.json";

        List<Transaction> transactions = new ArrayList<>();

        // 0. Is file .csv or .json?
        if (filename.endsWith(".csv")) {
            // 1. Read the file
            List<String> lines = readTheFile(filename);

            // 2. Create Transactions
            transactions = createTransactions(lines);
        } else if (filename.endsWith(".json")) {
            // 2a. Read the File as one string
            Path path = Paths.get(filename);
            byte[] bytes = Files.readAllBytes(path);
            String fileContents = new String(bytes);

            //2b. Create Transactions
            transactions = createJsonTransactions(fileContents);
        }

        // 3. Create People
        HashMap<String, Person> people = createPeople(transactions);

        // 3.a) Add transactions to peoples' accounts
        createAccounts(transactions, people);

        // 4. Ask the user for their command
        giveCommand(transactions, people);

    }

    public static List<String> readTheFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path);
        return lines;

    }

    public static List<Transaction> createTransactions(List<String> lines) {
        ArrayList<Transaction> ts = new ArrayList<Transaction>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            String[] bits = line.split(",");

            Transaction t = new Transaction();
            t.fromAccount = bits[1];
            t.amount = Double.parseDouble(bits[4]);
            t.toAccount = bits[2];
            t.date = LocalDate.parse(bits[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            t.narrative = bits[3];
            ts.add(t);

        }

        return ts;
    }

    public static List<Transaction> createJsonTransactions(String fileContents) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                LocalDate.parse(jsonElement.getAsString())
        );
        Gson gson = gsonBuilder.create();

        Transaction[] transactions = gson.fromJson(fileContents, Transaction[].class);
        return new ArrayList<Transaction>(Arrays.asList(transactions));
    }

    public static HashMap<String, Person> createPeople(List<Transaction> transactions) {
        HashMap<String, Person> hm = new HashMap<>();

        for (int i = 1; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);


            if (!hm.containsKey(transaction.fromAccount)) {
                Person p = new Person(transaction.fromAccount);
                hm.put(transaction.fromAccount, p);
            }
            if (!hm.containsKey(transaction.toAccount)) {
                Person p = new Person(transaction.toAccount);
                hm.put(transaction.toAccount, p);
            }
        }
        return hm;
    }

    public static void createAccounts(List<Transaction> transactions, HashMap<String, Person> hm) {

        for (int i = 1; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);

            String from = transaction.fromAccount;
            Person fromPerson = hm.get(from);
            fromPerson.transactions.add(transaction);

            String to = transaction.toAccount;
            Person toPerson = hm.get(to);
            toPerson.transactions.add(transaction);
        }

    }

    public static void giveCommand(List<Transaction> transactions, HashMap<String, Person> hm) {

        System.out.print("Would you like to \"List all\" total transactions or \"List Account name\" ");

        if (userinput.hasNextLine()) {
            String lineOfText = userinput.nextLine();

            if (lineOfText.equals("List all")) {
                for (Person person : hm.values()) {
                    System.out.println(person.getSummary());
                }
            } else if (lineOfText.startsWith("List")) {
                String name = lineOfText.substring(5);
                System.out.print(hm.get(name));
            }
        }


    }
}