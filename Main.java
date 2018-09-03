package com.company;

import java.io.File;
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

    public static void main(String[] args) {
        File file = new File("Jabberwocky.txt");

        //declare and initialize the hashmap, wordCount
        HashMap<String, Integer> wordCount = new HashMap<>();

        //counter to keep track of total number of words in text
        int totalAmountOfWords = 0;

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

                    //increment by one in each iteration of the loop
                    ++totalAmountOfWords;
                }
            }catch (Exception e){
                System.out.print(e);
            }
        }
        displayHashmap(wordCount);

        System.out.println("Total number of words in the text   =>  " + totalAmountOfWords);
    }
}
