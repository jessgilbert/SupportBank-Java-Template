package training.supportbank;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String args[]) throws IOException {
        // Your code here!
        Path path = Paths.get("C:\\Users\\JJG\\Work\\Training\\SupportBank-Java-Template\\2Transactions2014.csv");
        List<String> lines = Files.readAllLines(path);

        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();

        String regex = "[A-Za-z]*";


        String[] header = nextLine.split(regex);
        System.out.println(Arrays.toString(header));

        while (scanner.hasNext()) {
            String[] row = scanner.nextLine().split(regex);

            System.out.println(Arrays.toString(row));
            System.out.println(row[0]);
        }
        }
    }



}

