import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Tail {

    String sourcePath = "C:\\Users\\TEMP\\IdeaProjects\\ConsoleApp\\src\\main\\resources\\";

    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> fileStrings = new ArrayList<>();
        FileInputStream fis;
        try {
            fis = new FileInputStream(sourcePath + fileName);
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) fileStrings.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileStrings;
    }

    public List<String> readFromConsole() {
        List<String> consoleStrings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text:");
        System.out.println("Enter \"END\" to end the input.");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("END")) break;
            consoleStrings.add(line);
        }
        scanner.close();

        return consoleStrings;
    }

    public void extractSymbols(List<String> strings, int symbolsToExtract) {
        List<String> output = new ArrayList<>();
        String[] arrayStrings = strings.toArray(new String[0]);

        for (int i = arrayStrings.length - 1; i >= 0 && symbolsToExtract > 0; --i) {
            String currentString = arrayStrings[i];
            StringBuilder sb = new StringBuilder();
            for (int j = currentString.length() - 1; symbolsToExtract > 0 && j >= 0; --j, --symbolsToExtract) {
                sb.append(currentString.charAt(j));
            }
            output.add(0, sb.reverse().toString());
        }

        for (String string : output) {
            System.out.println(string);
        }
    }

    public void extractStrings(List<String> strings, int stringsToExtract) {
        List<String> output = strings;

        if (strings.size() >= stringsToExtract) {
            output = strings.subList(strings.size() - stringsToExtract, strings.size());
        }

        for (String string : output) {
            System.out.println(string);
        }
    }
    public void setOutToFile(String outputFileName) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(sourcePath + outputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(ps);
    }
}

