package training.supportbank;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        for (String line: lines) {

            String[] bits = line.split(",");

            String narrative = bits[3];
            Transaction t = new Transaction();
            t.fromName = bits[1];
            t.transAmount = Double.parseDouble(bits[4]);
            t.toName = bits[2];
            t.transDate = LocalDate.parse(bits[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));


            System.out.println(t.fromName + " owes " +  toName + " " + transAmount + " since " + transDate);



        }


//        System.out.println(fileContents);
    }
}






