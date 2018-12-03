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

    public static ArrayList<String> maxFrequency (HashMap<String,Integer> wordCount){
        ArrayList<String> containMaxWords = new ArrayList<>();
        int maxFrequency = Collections.max(wordCount.values());
        for(String word: wordCount.keySet()){
            if(wordCount.get(word) == maxFrequency){
                containMaxWords.add(word);
            }
        }
        return containMaxWords;
    }

    public static ArrayList<String> minFrequency (HashMap<String,Integer> wordCount){
        ArrayList<String> containMinWords = new ArrayList<>();
        int minFrequency = Collections.min(wordCount.values());
        for(String word: wordCount.keySet()){
            if(wordCount.get(word) == minFrequency){
                containMinWords.add(word);
            }
        }
        return containMinWords;
    }

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

        int maxWordLength = 0;

        int totalLenOfAllWords = 0;

        //if file exists, begin reading the file
        if(file.exists()){
            try{
                Scanner scan = new Scanner(file);
                while(scan.hasNext()){

                    //get each token
                    String word = scan.next();

                    //make each word lowercase and get rid of all punctuation
                    word = word.toLowerCase().replaceAll("\\p{Punct}","");

                    addToHashMap(wordCount,word);

                    totalLenOfAllWords += word.length();

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
        ArrayList<String> mostFrequentlyAppearedWords = maxFrequency(wordCount);
        for(String word: mostFrequentlyAppearedWords){
            System.out.print(word + " ");
        }
        System.out.println(); System.out.println();

        System.out.println("Word(s) that appeared the least in the text: ");
        ArrayList<String> leastFrequentlyAppearedWords = minFrequency(wordCount);
        for(String word: leastFrequentlyAppearedWords){
            System.out.print(word + " ");
        }
        System.out.println(); System.out.println();

        System.out.println("The longest word(s) in the text: ");
        ArrayList<String> longestLengthWords = longestLengthWord(wordCount, maxWordLength);
        for(String word: longestLengthWords){
            System.out.print(word + " ");
        }
        System.out.println(); System.out.println();

        System.out.println("Average word length in the text   =>  " + totalLenOfAllWords/totalAmountOfWords);
        System.out.println("Total number of characters in the text   =>  " + totalLenOfAllWords);
        System.out.println("Total number of words in the text   =>  " + totalAmountOfWords);
        System.out.println();

        System.out.println("The frequency in which each word appeared in the text: ");
        displayHashmap(wordCount);
    }
}
