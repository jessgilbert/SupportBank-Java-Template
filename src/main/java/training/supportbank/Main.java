package training.supportbank;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

            String transDate = bits[0];
            String fromName = bits[1];
            String toName = bits[2];
            String narrative = bits[3];
            String transAmount = bits[4];

            System.out.println(bits[2]);



        }


//        System.out.println(fileContents);
    }
}






