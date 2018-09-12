package training.supportbank;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\Users\\JJG\\Work\\Training\\SupportBank-Java-Template\\2Transactions2014.csv");
        List<String> lines = Files.readAllLines(path);

//        byte[] bytes = Files.readAllBytes(path);
//        String fileContents =  new String(bytes);


//        Pattern p = Pattern.compile("(.*),(.*),(.*),(.*),(.*)");
//        Matcher m = p.matcher(fileContents);

        List<Person> people = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            String[] bits = line.split(",");

            String narrative = bits[3];
            Transaction t = new Transaction();
            t.fromName = bits[1];
            t.transAmount = Double.parseDouble(bits[4]);
            t.toName = bits[2];
            t.transDate = LocalDate.parse(bits[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Person p = new Person(t.fromName);
            people.add(p);


            System.out.println(t.fromName + " owes " +  t.toName + " " + t.transAmount + " since " + t.transDate);



        }

        System.out.println("There are " + people.size() + " people");
        for (Person p : people) {
            System.out.println(p.name);
        }
    }
}
