import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Tail{

    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> fileStrings = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) fileStrings.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileStrings;
    }

    public ArrayList<String> readFromConsole() {
        ArrayList<String> consoleStrings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in:");
        while (scanner.hasNextLine() && !scanner.nextLine().equals("END")) {
            consoleStrings.add(scanner.nextLine());
            if (scanner.nextLine().equals("END")) break;
        }
        scanner.close();
        return consoleStrings;
    }

    public String extractSymbols(ArrayList<String> strings, int symbolsToExtract) {
        String text = strings.toString();
        return text.substring(text.length() - symbolsToExtract, text.length() - 1);
    }

    public String extractStrings(ArrayList<String> strings, int stringsToExtract) {
        return strings.subList(strings.size() - stringsToExtract, strings.size() - 1).toString();
    }

    /* public void fileOutput(String outputStrings, String outputName) {
        try {
            FileWriter writer = new FileWriter(outputName);
            writer.write(outputStrings);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consoleOutput(String outputStrings) {
        System.out.println(outputStrings);
    } */

    }

