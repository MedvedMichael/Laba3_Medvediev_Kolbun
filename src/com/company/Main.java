package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        MyHashMap<String, String>  dictionary = getDictionary();
        dictionary = new MyHashMap<>();
        String[] words = getWords();
        for (String word : words) {
            System.out.println(word);
        }
    }


    private static MyHashMap<String, String> getDictionary () throws FileNotFoundException {
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        File file = new File("dict_processed.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            int i = line.indexOf(';');
            String keyWord = line.substring(0, i);
            String defWord = line.substring(i);
            dictionary.put(keyWord, defWord);
        }
        dictionary.printAll();
        return dictionary;
    }

    private static String[] getWords() {
        System.out.println("Enter the sentence: ");
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine().replaceAll(",", "").replaceAll("\\s+", " ");
        String[] words = sentence.split(" ");

        System.out.println(sentence);
        return words;
    }

}

// I like noses, hair, shit.