package com.company;
/**
 *
 * @author Michael Bila
 * @version 2015.28.04
 */

import java.util.Random;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class deal
{
    private int playerNum;
    private int player1Hand;
    private int player2Hand;
    private int dealerHand;
    private BufferedReader reader;
    private String[][] scores;

    /**
     * Constructor for objects of class deal
     */
    public deal()
    {
        player1Hand = 0;
        player2Hand = 0;
        dealerHand = 0;
    }

    //overload
    public deal(int player1Hand, int player2Hand) {
        this.player1Hand = player1Hand;
        this.player2Hand = player2Hand;
        this.dealerHand = dealerHand;
    }

    public void readInScores(String fileName) {
        scores = new String[4][2];
        int counter = 0;
        try {
            String line;
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                //format for csv is player name, current hand in digits
                String[] rowData = line.split(",");
                scores[counter][0] = rowData[0];
                scores[counter][1] = rowData[1];
                scores[counter][2] = rowData[2];
                counter++;
            }
            reader.close();
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }

    public void initiateReadScores() {
        player1Hand = Integer.parseInt(scores[0][1]);
        player2Hand = Integer.parseInt(scores[1][1]);
        dealerHand = Integer.parseInt(scores[2][1]);
    }

    public void deal1Card(int playerNum)
    {
        Random R = new Random();
        int low = 1;
        int high = 52;
        if (playerNum == 1) {
            int r = R.nextInt(high - low) + low;
            player1Hand = r + player1Hand;
            printCurrentHand(playerNum);
        } else if (playerNum == 2) {
            int r = R.nextInt(high - low) + low;
            player2Hand = r + player2Hand;
            printCurrentHand(playerNum);
        } else {
            int r = R.nextInt(high - low) + low;
            dealerHand = r + dealerHand;
            printCurrentHand(playerNum);
        }
    }

    public void printCurrentHand(int playerNum)
    {
        if (playerNum == 1) {
            System.out.println("Player 1's hand: " + player1Hand);
        } else if (playerNum == 2) {
            System.out.println("Player 2's hand: " + player2Hand);
        } else {
            System.out.println("Dealer's hand: " + dealerHand);
        }
    }

    public void hitOrHold(int playerNum, int choice) {
        if (playerNum != 3) {
            switch(choice) {
                case 1:
                    deal1Card(playerNum);
                    break;
                case 2:
                    if (playerNum == 1) {
                        System.out.println("Player " + playerNum + " is holding with a value of " + player1Hand);
                    } else if (playerNum == 2) {
                        System.out.println("Player " + playerNum + " is holding with a value of " + player2Hand);
                    }
                default:
                    System.out.println("That's not a choice.");
            }
        } else {
            if (dealerHand > 17) {
                System.out.println("The dealer is holding with a value of " + dealerHand);
            } else {
                deal1Card(playerNum);
            }
        }
    }

    public void checkWinner() {
        if (player1Hand < 21 && player1Hand > player2Hand) {
            System.out.println("Player 1 wins with a " + player1Hand);
            player1Hand = 0;
            player2Hand = 0;
        } else if (player2Hand < 21 && player2Hand > player1Hand) {
            System.out.println("Player 2 wins with a " + player2Hand);
            player1Hand = 0;
            player2Hand = 0;
        } else if (player2Hand > 21 && player1Hand > 21) {
            System.out.println("Both players had more than 21. Everyone loses!");
            player1Hand = 0;
            player2Hand = 0;
        } else { //must be a tie if it hits this point
            System.out.println("Both players had the same hand. What are the odds?");
        }
    }

    public String toString() {
        return "Player 1 hand: " + player1Hand + ",, "
                + "Player 2 hand: " + player2Hand + ",, "
                + "Dealer hand: " + dealerHand;
    }
}