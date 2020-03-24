package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        MyHashMap<String, String> dictionary = getDictionary();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //dictionary = new MyHashMap<>();
            String[] words = getWords(scanner);
            if (words.length == 0) {
                scanner.close();
                return;
            }
            printDefinitions(words, dictionary);
        }

    }

    public static void prettyOutput(String text) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        String ANSI_BLACK_TEXT = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        System.out.print(ANSI_RED + text + ": " + ANSI_RESET);
    }

    public static void printDefinitions(String[] words, MyHashMap<String, String> dictionary) throws FileNotFoundException {
        for (String word : words) {
            prettyOutput(word);
            System.out.println(dictionary.get(word.toUpperCase()));
        }
    }

    private static MyHashMap<String, String> getDictionary() throws FileNotFoundException {
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        File file = new File("dict_processed.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int i = line.indexOf(';');
            String keyWord = line.substring(0, i);
            String defWord = line.substring(i + 1);
            dictionary.put(keyWord, defWord);
        }
        return dictionary;
    }

    private static String[] getWords(Scanner scanner) {
        System.out.print("\nEnter the sentence: ");

        String[] words = new String[0];
        if (scanner.hasNextLine()) {
            String sentence = scanner.nextLine().replaceAll("\\W", " ").replaceAll("\\s+", " ").toLowerCase();
            words = sentence.split(" ");
        }
        return words;
    }
}