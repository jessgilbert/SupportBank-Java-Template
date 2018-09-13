package training.supportbank;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.sound.sampled.Line;
import java.io.File;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String accountDetails = new String();
        createAccounts(accountDetails);


        // 4. Ask the user for their command


        // 5. Print out details


//        byte[] bytes = Files.readAllBytes(path);
//        String fileContents =  new String(bytes);


//        Pattern p = Pattern.compile("(.*),(.*),(.*),(.*),(.*)");
//        Matcher m = p.matcher(fileContents);

//
//
//            System.out.println(t.fromName + " owes " + t.toName + " " + t.transAmount + " since " + t.transDate);
//        }
//
//        System.out.print("Please enter and Account holder name from above list");
//        userinput.nextLine();
//        if(userinput.hasNextLine()) {
//            String lineOfText = userinput.nextLine();
//        }
    }

    public static List<String> readTheFile() throws IOException {
        Path path = Paths.get("C:\\Users\\JJG\\Work\\Training\\SupportBank-Java-Template\\2Transactions2014.csv");
        List<String> lines = Files.readAllLines(path);
        return lines;

    }

    public static List<Transaction> createTransactions(List<String> lines) {
        ArrayList<Transaction> ts = new ArrayList<Transaction>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            String[] bits = line.split(",");

            String narrative = bits[3];
            Transaction t = new Transaction();
            t.fromName = bits[1];
            t.transAmount = Double.parseDouble(bits[4]);
            t.toName = bits[2];
            t.transDate = LocalDate.parse(bits[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

    public static void createAccounts(String name, Double transactions) {

        for (int i = 1; i < transactions.name(); i++) {
            Transaction transaction = transactions.get(i);

            if (Transaction) {


            }

        }
    }
}

//    public static Hashmap<String, Person> createPerson(List<Transaction>) {
//
//
//    }
//        List<Person> people = new ArrayList<>();
//
//
//
//            System.out.println();
//
//
//            hm.put(, userinput );
//            hm.get(people);
//
//            if(hm.containsKey(Person))


//        System.out.println("There are " + people.size() + " people");
//        for (Person p : people) {
//            System.out.println(p.name);
//
//            if (p.name.equals(p));
//
//        }

//    private void setName(String nextLine) {
//    }

