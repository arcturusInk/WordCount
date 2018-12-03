package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> winnersId = new ArrayList();             //Store the IDs of the winners
        HashMap<Integer, Integer> rankingOfHand = new HashMap<>();  //Store the IDs and ranking of hand
        ArrayList<Player> players = new ArrayList<>();              //Store the players in the game

        Scanner scan = new Scanner(System.in);
        int numOfPlayers = scan.nextInt();

        if(numOfPlayers > 0 && numOfPlayers < 24){
            for(int i = 0; i < numOfPlayers; ++i){
                int ID = scan.nextInt();                                //Get player's ID
                Card[] playersHand = new Card[3];                       //Cards array declared and initialized
                for(int j = 0; j < 3; ++j){
                    String c = scan.next();
                    Card aCard = new Card(c.charAt(0), c.charAt(1));    //Cards are created
                    playersHand[j] = aCard;                             //Store cards into the cards array
                }
                Player aPlayer = new Player(ID, playersHand);           //Players are created with unique IDs and individual hands
                players.add(aPlayer);                                   //Players added to players arrayList
            }
        }else{
            System.out.println("You gave an invalid number of players");
        }

        //Get the ranking/value of each player's hand
        //Store the player's ID and ranking/value into a HashMap
        for(Player p: players){
            int rank = Poker.valueOfHand(p.getPlayersHand());
            rankingOfHand.put(p.getID(), rank);
        }

        //Get the largest ranking/value among the hands
        int maxHandCount = Collections.max(rankingOfHand.values());

        //Finds the IDs with the largest ranking, store them into an ArrayList
        for(Integer key : rankingOfHand.keySet()){
            if(rankingOfHand.get(key) == maxHandCount){
                winnersId.add(key);
            }
        }

        //Sort the ArrayList in ascending order
        Collections.sort(winnersId);

        //Output the ID(s) of the winners(s)
        for(Integer winner: winnersId) {
            if (winner == winnersId.get(winnersId.size() - 1)) {
                System.out.print(winner);
            } else {
                System.out.print(winner + " ");
            }
        }
    }
}
