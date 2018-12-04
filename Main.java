package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    /*loops through the HashMap to display it*/
    //@param hashmp - takes in the hashmap that the function will display
    public static void displayHashmap(HashMap<String,Integer> hashmp){
        for(String key : hashmp.keySet()){
            System.out.println(key + "   =>   " + hashmp.get(key));
        }
    }

    /*loops through the ArrayList to display it*/
    //@param  arrayList - takes in the arrayList that the function will display
    public static void displayArrayList(ArrayList<String> arrayList){
        for(String word: arrayList){
            System.out.print(word + " ");
        }
        System.out.println(); System.out.println();
    }

    /*if the word hasn't appeared before in the text, add the word as a key onto the hashmap with count 1
     if key already exists, then increases the amount of time word appears by one */
    //@param wordCount - the hashmap onto which we store/access the amount of time a word appears in a text
    //@param word - one word from the text
    public static void addToHashMap (HashMap<String,Integer> wordCount, String word){
        Integer count = 1;
        if(wordCount.containsKey(word)){
            Integer cnt = wordCount.get(word);
            wordCount.put(word, ++cnt);
        }else{
            wordCount.put(word, count);
        }
    }

    //Finds the word(s) that appeared the maximum or minimum times in the text
    //@param wordCount - the hashmap onto which we store/access the amount of time a word appears in a text
    //@param toMatch - can be the maximum or minimum frequency value
    //@return containWords - an arrayList that contains the words
    public static ArrayList<String> findMaxMinFrequency (HashMap<String,Integer> wordCount, int toMatch){
        ArrayList<String> containWords = new ArrayList<>();
        for(String word: wordCount.keySet()){
            if(wordCount.get(word) == toMatch){
                containWords.add(word);
            }
        }
        return containWords;
    }

    //Finds the longest length word in the text
    //@param wordCount - the hashmap onto which we store/access the amount of time a word appears in a text
    //@param maxWordLength - the length of the longest word
    //@return longestWords - an arrayList that contains the longest-lenght word(s)
    public static ArrayList<String> longestLengthWord (HashMap<String,Integer> wordCount, int maxWordLength){
        ArrayList<String> longestWords = new ArrayList<>();
        for(String word: wordCount.keySet()){
            if(word.length() == maxWordLength){
                longestWords.add(word);
            }
        }
        return longestWords;
    }

    public static void main(String[] args) {
        File file = new File("Jabberwocky.txt");

        //declare and initialize the hashmap, wordCount
        HashMap<String, Integer> wordCount = new HashMap<>();

        //counter to keep track of total number of words in text
        int totalAmountOfWords = 0;

        //counter to keep track of the length of the longest word
        int maxWordLength = 0;

        //counter to keep track of total number of chars in text
        int totalCharLenOfAllWords = 0;

        //if file exists, begin reading the file
        if(file.exists()){
            try{
                Scanner scan = new Scanner(file);
                while(scan.hasNext()){

                    //get each token
                    String word = scan.next();

                    //Taking care of basic pre-processing of data: make each word lowercase and get rid of all punctuation
                    word = word.toLowerCase().replaceAll("\\p{Punct}","");

                    addToHashMap(wordCount,word);

                    //getting the length of all the chars in each word
                    totalCharLenOfAllWords += word.length();

                    //finding the length of the longest word
                    if(word.length() > maxWordLength){
                        maxWordLength = word.length();
                    }

                    //increment by one in each iteration of the loop
                    ++totalAmountOfWords;
                }
            }catch (Exception e){
                System.out.print(e);
            }
        }

        System.out.println("Word(s) that appeared the most in the text: ");
        int maxFrequency = Collections.max(wordCount.values());
        ArrayList<String> mostFrequentlyAppearedWords = findMaxMinFrequency(wordCount, maxFrequency);
        displayArrayList(mostFrequentlyAppearedWords);

        System.out.println("Word(s) that appeared the least in the text: ");
        int minFrequency = Collections.min(wordCount.values());
        ArrayList<String> leastFrequentlyAppearedWords = findMaxMinFrequency(wordCount, minFrequency);
        displayArrayList(leastFrequentlyAppearedWords);

        System.out.println("The longest word(s) in the text: ");
        ArrayList<String> longestLengthWords = longestLengthWord(wordCount, maxWordLength);
        displayArrayList(longestLengthWords);

        System.out.println("Average word length in the text:  " + totalCharLenOfAllWords/totalAmountOfWords);
        System.out.println("Total number of characters in the text:  " + totalCharLenOfAllWords);
        System.out.println("Total number of words in the text: " + totalAmountOfWords);
        System.out.println();

        System.out.println("Frequency in which each word appeared in the text: ");
        displayHashmap(wordCount);
    }
}
